package com.cyclone.dineflow.dto.requestdto;

import java.util.List;

public record OrderItemRequestDto(
        String menuItemId,
        String variantId,
        Integer quantity,
        String notes
//        List<String> addOnIds
) {
}
