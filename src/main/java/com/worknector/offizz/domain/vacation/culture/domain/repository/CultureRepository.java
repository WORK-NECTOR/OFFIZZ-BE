package com.worknector.offizz.domain.vacation.culture.domain.repository;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CultureRepository extends JpaRepository<Culture, Long>, CultureDslRepository {
    Optional<Culture> findByContentidAndTitle(String contendid, String title);
}
