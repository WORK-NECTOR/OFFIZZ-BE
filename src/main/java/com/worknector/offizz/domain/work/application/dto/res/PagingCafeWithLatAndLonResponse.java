package com.worknector.offizz.domain.work.application.dto.res;

import java.util.List;

public record PagingCafeWithLatAndLonResponse(
        List<CafeWithLatAndLon> offices,
        int totalPage
) {}
