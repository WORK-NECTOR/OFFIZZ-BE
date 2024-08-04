package com.worknector.offizz.domain.office.application.dto.res;

import java.util.List;

public record PagingRecOfficeResponse(
        List<RecOffice> recOffices,
        int totalPage
) {
}
