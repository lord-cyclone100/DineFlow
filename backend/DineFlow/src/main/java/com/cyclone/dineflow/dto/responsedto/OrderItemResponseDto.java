package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.OrderItemStatus;

public record OrderItemResponseDto(
        String orderId,
        String menuItemName,
        String menuItemVariantName,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        OrderItemStatus orderItemStatus,
        String notes
) {
}
