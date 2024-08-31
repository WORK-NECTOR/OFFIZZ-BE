package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.WorkationVacationKeyword;
import com.worknector.offizz.domain.workation.domain.repository.WorkationKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkationKeywordSaveService {

    private final WorkationKeywordRepository workationKeywordRepository;

    public WorkationVacationKeyword save(WorkationVacationKeyword workationVacationKeyword) {
        return workationKeywordRepository.save(workationVacationKeyword);
    }
}
