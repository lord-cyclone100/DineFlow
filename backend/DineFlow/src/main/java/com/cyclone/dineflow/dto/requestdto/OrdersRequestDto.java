package com.cyclone.dineflow.dto.requestdto;

import com.cyclone.dineflow.entity.data.OrderType;

import java.util.List;

public record OrdersRequestDto(
        String branchId,
        String tableId,
        OrderType orderType,
        List<OrderItemRequestDto> orderItems,
//        Integer totalAmount,
        Integer discount,
        Integer tax,
        String specialInstructions,
        String deliveryAddress
) {}
