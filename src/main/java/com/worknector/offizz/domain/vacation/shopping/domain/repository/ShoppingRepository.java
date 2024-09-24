package com.worknector.offizz.domain.vacation.shopping.domain.repository;

import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    Optional<Shopping> findByContentidAndTitle(String contendid, String title);
}
