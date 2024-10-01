package com.worknector.offizz.domain.workation.domain.entity;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkTodo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_id", nullable = false)
    private Daily daily;

    private int icon;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Builder.Default
    private boolean isComplete = false;

    private LocalTime planTime;

    private LocalTime actualTime;

    private LocalTime startTime;

    private LocalTime endTime;

    public void updateFin(LocalTime actualTime, LocalTime startTime, LocalTime endTime) {
        this.isComplete = true;
        this.actualTime = actualTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
