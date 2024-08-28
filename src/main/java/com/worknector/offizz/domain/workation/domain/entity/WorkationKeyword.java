package com.worknector.offizz.domain.workation.domain.entity;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkationKeyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workationKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workation_id", nullable = false)
    private Workation workation;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private Keyword keyword;
}
