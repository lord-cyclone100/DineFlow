package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.OrderType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrdersResponseDto(
        String userName,
        String restaurantName,
        String branchName,
        String tableNumber,
        OrderType orderType,
        Integer totalAmount,
        Integer discount,
        Integer tax,
        Integer finalAmount,
        String specialInstructions,
        String message
) {}
