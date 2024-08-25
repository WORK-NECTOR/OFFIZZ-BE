package com.worknector.offizz.domain.vacation.nature.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long natureId;

    private String addr1;

    private String mapx;

    private String mapy;

    private String modifiedtime;

    private String tel;

    private String title;

    private String contentid;

    private String contenttypeid;

    private String createdtime;

    private String firstimage;

    private String firstimage2;

    private String areacode;

    private String sigungucode;

    private String cat1;

    private String cat2;

    private String cat3;

    private int hit;
}
