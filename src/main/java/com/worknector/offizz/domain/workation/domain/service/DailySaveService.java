package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailySaveService {
    private final DailyRepository dailyRepository;

    public void save(Daily daily) {
        dailyRepository.save(daily);
    }
}
