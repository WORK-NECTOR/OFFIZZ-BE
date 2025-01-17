package com.worknector.offizz.domain.workation.domain.entity;

import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoRequest;
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
public class VacationTodo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_id", nullable = false)
    private Daily daily;

    private int icon;

    @Column(nullable = false)
    private String name;

    private String locate;

    private Double rating;

    private String comment;

    private String image;

    @Column(nullable = false)
    @Builder.Default
    private boolean isComplete = false;

    public boolean isComplete() {
        return isComplete;
    }

    public void updateVacationTodoFin(VacationTodoFinRequest request) {
        this.locate = request.locate();
        this.rating = request.rating();
        this.comment = request.comment();
        this.isComplete = true;
        if (request.image() != null)
            this.image = request.image();
        if (request.image() == null)
            this.image = "https://offizz-s3.s3.ap-northeast-2.amazonaws.com/offizz-record/emptyVacation.png";
    }

    public void updateVacationTodo(VacationTodoFinRequest request) {
        this.locate = request.locate();
        this.rating = request.rating();
        this.comment = request.comment();
        this.isComplete = true;
        if (request.image() != null)
            this.image = request.image();
        else
            this.image = "https://offizz-s3.s3.ap-northeast-2.amazonaws.com/offizz-record/emptyVacation.png";
    }

    public void updateNotFin() {
        this.locate = null;
        this.rating = null;
        this.comment = null;
        this.isComplete = false;
        this.image = null;
    }
}
