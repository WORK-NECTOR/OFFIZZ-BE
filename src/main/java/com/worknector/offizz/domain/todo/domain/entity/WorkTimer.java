package com.worknector.offizz.domain.todo.domain.entity;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkTimer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workTimerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_todo_id", nullable = false)
    private WorkTodo workTodo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
