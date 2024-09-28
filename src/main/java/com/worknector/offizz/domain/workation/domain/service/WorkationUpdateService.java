package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.EntireRetrospectRequest;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationRetrospect;
import com.worknector.offizz.domain.workation.domain.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkationUpdateService {
    private final WorkationRepository workationRepository;

    public Workation updateWorkationFin(User user, EntireRetrospectRequest request) {
        Workation workation = workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        workation.finishWorkation(new WorkationRetrospect(request.score(), request.review()));
        return workation;
    }
}
