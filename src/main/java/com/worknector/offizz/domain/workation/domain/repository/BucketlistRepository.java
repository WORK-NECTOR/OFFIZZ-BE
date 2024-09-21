package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BucketlistRepository extends JpaRepository<Bucketlist, Long> {
    List<Bucketlist> findAllByWorkation(Workation workation);
}
