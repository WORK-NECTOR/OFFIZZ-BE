package com.worknector.offizz.domain.accommodation.domain.service;

import com.worknector.offizz.domain.accommodation.domain.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationGetService {
    private final AccommodationRepository accommodationRepository;
}
