package com.worknector.offizz.domain.work.office.application.dto.res;

import java.util.List;

public record PagingOfficeWithLatAndLonResponse(
    List<OfficeWithLatAndLon> offices,
    int totalPage
) {}
