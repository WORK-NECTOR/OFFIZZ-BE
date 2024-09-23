package com.worknector.offizz.domain.vacation.culture.domain.service;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.repository.CultureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CultureGetService {
    private final CultureRepository cultureRepository;

    public List<Culture> getAllCultureBySearch(String search, double lat, double lon) {
        return cultureRepository.getAllCultureBySearch(search, lat, lon);
    }
}
