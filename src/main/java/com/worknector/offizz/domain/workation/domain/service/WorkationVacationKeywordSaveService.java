package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.WorkationVacationKeyword;
import com.worknector.offizz.domain.workation.domain.repository.WorkationVacationKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkationVacationKeywordSaveService {

    private final WorkationVacationKeywordRepository workationVacationKeywordRepository;

    public WorkationVacationKeyword save(WorkationVacationKeyword workationVacationKeyword) {
        return workationVacationKeywordRepository.save(workationVacationKeyword);
    }
}
