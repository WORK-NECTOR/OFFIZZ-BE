package com.worknector.offizz.domain.workation.domain.entity;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Daily extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workation_id", nullable = false)
    private Workation workation;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(value = STRING)
    private ConditionType workCondition;

    @Enumerated(value = STRING)
    private ConditionType vacationCondition;

    private String dailyY;

    private String dailyW;

    private String dailyT;
}
