package com.worknector.offizz.domain.todo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VacationTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_todo_id", nullable = false)
    private DailyTodo dailyTodo;

    private int icon;

    @Column(nullable = false)
    private String name;

    private String locate;

    private Double rating;

    private String comment;

    @Column(nullable = false)
    @Builder.Default
    private boolean isComplete = false;
}
