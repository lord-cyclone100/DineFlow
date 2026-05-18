package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.responsedto.OrderItemResponseDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponseDto> getOrderItems(String orderId);

    String updateOrderItemsStatus(String orderItemId, String status);
}
