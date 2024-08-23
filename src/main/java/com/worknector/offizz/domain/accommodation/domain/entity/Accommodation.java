package com.worknector.offizz.domain.accommodation.domain.entity;

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
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;

    private String addr1;

    private String addr2; // 와이어프레임상 사용하지 않는 것으로 보이는데, 불필요시 삭제

    private String mapx; // map 관련 또한 사용하지 않는 것으로 보이는데, 불필요시 삭제

    private String mapy;

    private String mlevel;

    private String modifiedtime; // 업데이트시 사용?

    private String tel;

    private String title;

    private String contentid;

    private String firstimage;

    private String firstimage2;
}
