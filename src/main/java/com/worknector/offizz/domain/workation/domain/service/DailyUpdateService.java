package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.application.dto.req.RetrospectRequest;
import com.worknector.offizz.domain.workation.domain.entity.Daily;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DailyUpdateService {

    public void updateDailyRetrospect(Daily daily, RetrospectRequest retrospectRequest) {
        daily.updateDailyRetrospect(retrospectRequest);
    }
}
