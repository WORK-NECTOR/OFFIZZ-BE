package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import com.worknector.offizz.domain.workation.domain.entity.Workation;

public class BucketlistMapper {

    private BucketlistMapper() {
        throw new IllegalArgumentException();
    }

    public static Bucketlist mapToBucketlist(Workation workation, String name, String address) {
        return Bucketlist.builder()
                .workation(workation)
                .name(name)
                .address(address)
                .build();
    }
}
