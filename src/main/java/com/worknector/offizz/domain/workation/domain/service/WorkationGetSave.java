package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.domain.entity.WorkKeyword;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.repository.WorkationRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkationVacationKeywordRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkationWorkKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkationGetSave {
    private final WorkationRepository workationRepository;
    private final WorkationWorkKeywordRepository workKeywordRepository;
    private final WorkationVacationKeywordRepository vacationKeywordRepository;

    public Workation findByUser(User user) {
        return workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<WorkKeyword> findWorkKeyword(User user) {
        Workation workation = workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return workKeywordRepository.findAllByWorkation(workation);
    }
}
