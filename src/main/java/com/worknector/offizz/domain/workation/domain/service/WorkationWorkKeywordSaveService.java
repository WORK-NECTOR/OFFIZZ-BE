package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.WorkationWorkKeyword;
import com.worknector.offizz.domain.workation.domain.repository.WorkationWorkKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkationWorkKeywordSaveService {

    private final WorkationWorkKeywordRepository workationWorkKeywordRepository;

    public WorkationWorkKeyword save(WorkationWorkKeyword workationWorkKeyword) {
        return workationWorkKeywordRepository.save(workationWorkKeyword);
    }
}
