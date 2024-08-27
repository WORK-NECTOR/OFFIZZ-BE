package com.worknector.offizz.domain.work.application.dto.res;

import java.util.List;

public record PagingOfficeWithLatAndLonResponse(
    List<OfficeWithLatAndLon> offices,
    int totalPage
) {}
