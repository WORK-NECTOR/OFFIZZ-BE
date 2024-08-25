package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;

import java.util.List;

public interface NatureDslRepository {
    List<Nature> findAllNatureBySearch(String search);
}
