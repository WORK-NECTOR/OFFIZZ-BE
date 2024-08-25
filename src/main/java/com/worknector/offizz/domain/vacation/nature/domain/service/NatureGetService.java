package com.worknector.offizz.domain.vacation.nature.domain.service;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.nature.domain.repository.NatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NatureGetService {
    private final NatureRepository natureRepository;

    public List<Nature> getAllNatureBySearch(String search) {
        return natureRepository.findAllNatureBySearch(search);
    }
}
