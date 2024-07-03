package com.worknector.offizz.domain.workation.domain.entity;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Workation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String reason;

    @Column(nullable = false)
    private String locate;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalTime startCoreTime;

    @Column(nullable = false)
    private LocalTime endCoreTime;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private WorkationKeyword keyword;

    private String goal;
}
