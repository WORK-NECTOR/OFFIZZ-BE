package com.worknector.offizz.domain.work.office.application.dto.res;

import java.util.List;

public record PagingRecOfficeResponse(
        List<RecOffice> recOffices,
        int totalPage
) {
}
