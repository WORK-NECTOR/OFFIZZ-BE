package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkationRepository extends JpaRepository<Workation, Long> {
    List<Workation> findAllByUserOrderByCreatedAtDesc(User user);
}
