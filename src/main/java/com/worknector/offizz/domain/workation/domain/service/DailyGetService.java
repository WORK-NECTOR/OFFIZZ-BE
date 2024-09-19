package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.repository.DailyRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DailyGetService {
    private final DailyRepository dailyRepository;
    private final WorkationRepository workationRepository;

    public Daily findDailyByWorkation(User user, int day) {
        Workation workation = workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
        return dailyRepository.findByWorkationAndDay(workation, day)
                .orElseThrow(IllegalAccessError::new);
    }
}
