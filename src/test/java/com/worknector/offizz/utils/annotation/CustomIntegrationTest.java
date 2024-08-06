package com.worknector.offizz.utils.annotation;

import com.worknector.offizz.global.config.swagger.SwaggerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.yml")
@ComponentScan(
        basePackages = {
                "com.worknector.offizz.global.config"
        },
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {SwaggerConfig.class}
        )
)
public @interface CustomIntegrationTest {

}
