package com.worknector.offizz.domain.work.cafe.application.dto.res;

import java.util.List;

public record PagingCafeWithLatAndLonResponse(
        List<CafeWithLatAndLon> offices,
        int totalPage
) {}
