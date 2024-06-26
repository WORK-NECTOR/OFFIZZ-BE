package com.worknector.offizz.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.exception.UserNotFoundException;
import com.worknector.offizz.global.config.jwt.constant.Type;
import com.worknector.offizz.global.config.jwt.exception.InvalidTokenException;
import com.worknector.offizz.global.config.jwt.auth.AuthDetails;
import com.worknector.offizz.global.config.jwt.auth.AuthDetailsService;
import com.worknector.offizz.global.config.jwt.exception.InvalidRefreshTokenException;
import com.worknector.offizz.global.config.jwt.exception.NoneRefreshTokenException;
import com.worknector.offizz.global.config.jwt.exception.TokenExpiredException;
import com.worknector.offizz.global.exception.ApplicationException;
import com.worknector.offizz.global.exception.ExceptionResponse;
import com.worknector.offizz.global.redis.RedisRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.List;

import static com.worknector.offizz.global.config.jwt.constant.Type.REFRESH;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private final AuthDetailsService authDetailsService;
    private final RedisRepository redisRepository;

    @Value("${jwt.secret-key}")
    private String secret;

    private static final String ROLE = "role";
    private static final String TYPE = "type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "application/json";
    private static final String CHARACTER_ENCODING = "UTF-8";

    public String checkRedis(Long id, HttpServletRequest request) {
        String refreshToken = request.getHeader(AUTHORIZATION).split(" ")[1];
        String redisToken = redisRepository.getValues(REFRESH.toString() + id)
                .orElseThrow(NoneRefreshTokenException::new);
        if (!redisToken.equals(refreshToken))
            throw new InvalidRefreshTokenException();
        Claims claims = parseClaims(refreshToken);
        return claims.get(ROLE).toString();
    }

    public void makeExpired(Long id) {
        redisRepository.deleteValues(REFRESH.toString() + id);
    }

    public Authentication getAuthentication(HttpServletResponse response, String token) throws UserNotFoundException {
        Claims claims = parseClaims(token);
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(claims.get(ROLE).toString()));
        User user = getDetails(response, claims).user();
        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    private AuthDetails getDetails(HttpServletResponse response, Claims claims) {
        try {
            return authDetailsService.loadUserByUsername(claims.getSubject());
        } catch (UserNotFoundException ex) {
            jwtExceptionHandler(BAD_REQUEST, response, ex);
            throw ex;
        }
    }

    public boolean validateToken(HttpServletResponse response, String token, Type type) {
        try {
            Claims claims = parseClaims(token);
            if (!claims.get(TYPE).equals(type.name()))
                throw new InvalidTokenException();
            return true;
        } catch (ExpiredJwtException e) {
            jwtExceptionHandler(OK, response, new TokenExpiredException());
            throw new TokenExpiredException();
        } catch (Exception e) {
            jwtExceptionHandler(OK, response, new InvalidTokenException());
            throw new InvalidTokenException();
        }
    }

    private Claims parseClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void jwtExceptionHandler(HttpStatus status, HttpServletResponse response, ApplicationException ex) {
        response.setStatus(status.value());
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        log.error("errorCode {}, errorMessage {}", ex.getCode(), ex.getMessage());
        try {
            String json = new ObjectMapper().writeValueAsString(
                    ResponseEntity.badRequest()
                            .body(new ExceptionResponse(ex.getCode(), ex.getMessage()))
            );
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
