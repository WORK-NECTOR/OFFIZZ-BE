package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketlistRepository extends JpaRepository<Bucketlist, Long> {
}