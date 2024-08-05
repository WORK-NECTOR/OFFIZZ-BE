package com.worknector.offizz.domain.vacation.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AreaCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaCodeId;

    private String code;

    private String name;
}
