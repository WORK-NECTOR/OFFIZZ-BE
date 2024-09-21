package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.repository.BucketlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketlistGetService {
    private final BucketlistRepository bucketlistRepository;

    public List<Bucketlist> byWorkation(Workation workation) {
        return bucketlistRepository.findAllByWorkation(workation);
    }
}
