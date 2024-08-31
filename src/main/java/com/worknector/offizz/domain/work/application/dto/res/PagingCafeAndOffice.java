package com.worknector.offizz.domain.work.application.dto.res;

import java.util.List;

public record PagingCafeAndOffice(
        List<CafeAndOffice> cafeAndOffices,
        int totalPage
) {
}
