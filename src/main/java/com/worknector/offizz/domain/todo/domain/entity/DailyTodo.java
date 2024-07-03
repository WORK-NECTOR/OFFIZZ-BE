package com.worknector.offizz.domain.todo.domain.entity;

import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DailyTodo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workation_id", nullable = false)
    private Workation workation;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private LocalDate date;
}
