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
public class BucketList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workation_id", nullable = false)
    private Workation workation;

    private String name;
}
