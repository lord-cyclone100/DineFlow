package com.cyclone.dineflow.dto.requestdto;

import com.cyclone.dineflow.entity.data.OrderType;

public record OrdersRequestDto(
        String branchId,
        String tableId,
        OrderType orderType,
        Integer totalAmount,
        Integer discount,
        Integer tax,
        String specialInstructions,
        String deliveryAddress
) {}
