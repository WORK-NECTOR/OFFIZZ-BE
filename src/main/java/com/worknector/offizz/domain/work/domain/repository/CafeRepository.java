package com.worknector.offizz.domain.work.domain.repository;

import com.worknector.offizz.domain.work.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeDslRepository {
    public Optional<Cafe> findByContentId(String contendId);
}
