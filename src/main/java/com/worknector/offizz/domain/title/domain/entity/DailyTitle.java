package com.worknector.offizz.domain.title.domain.entity;

import com.worknector.offizz.domain.todo.domain.entity.DailyTodo;
import com.worknector.offizz.global.entity.BaseEntity;
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
public class DailyTitle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTitleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_todo_id", nullable = false)
    private DailyTodo dailyTodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;
}
