package com.worknector.offizz.domain.work.cafe.domain.repository;

import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    public Optional<Cafe> findByContentId(String contendId);
}
