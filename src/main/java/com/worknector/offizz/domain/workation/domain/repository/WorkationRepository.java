package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkationRepository extends JpaRepository<Workation, Long> {
    List<Workation> findAllByUserOrderByCreatedAtDesc(User user);

    @Query("SELECT w FROM Workation w WHERE w.user = :user AND w.startDate <= :today AND w.endDate >= :today")
    List<Workation> findOngoingWorkations(@Param("user") User user, @Param("today") LocalDate today);

//    List<Workation> findAllByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
//        User user, LocalDate today, LocalDate today);
}
