package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    Optional<Daily> findByWorkationAndDay(Workation workation, int day);
    List<Daily> findAllByWorkation(Workation workation);
}
