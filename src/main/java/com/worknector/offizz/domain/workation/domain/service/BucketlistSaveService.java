package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import com.worknector.offizz.domain.workation.domain.repository.BucketlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BucketlistSaveService {

    private final BucketlistRepository bucketlistRepository;

    public Bucketlist save(Bucketlist bucketlist) {
        return bucketlistRepository.save(bucketlist);
    }
}
