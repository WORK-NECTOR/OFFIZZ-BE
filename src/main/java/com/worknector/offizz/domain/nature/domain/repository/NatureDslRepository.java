package com.worknector.offizz.domain.nature.domain.repository;

import com.worknector.offizz.domain.nature.domain.entity.Nature;

import java.util.List;

public interface NatureDslRepository {
    List<Nature> findAllNatureBySearch(String search);
}
