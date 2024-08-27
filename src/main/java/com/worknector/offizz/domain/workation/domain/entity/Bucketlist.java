package com.worknector.offizz.domain.workation.domain.entity;

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
public class Bucketlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bucketlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workation_id", nullable = false)
    private Workation workation;

    private String name;

    private String address;

    @Column(nullable = false)
    @Builder.Default
    private boolean isComplete = false;
}
