package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.responsedto.OrderItemResponseDto;
import com.cyclone.dineflow.dtomapper.OrderItemResponseDtoMapper;
import com.cyclone.dineflow.entity.OrderItem;
import com.cyclone.dineflow.entity.data.OrderItemStatus;
import com.cyclone.dineflow.repository.OrderItemRepository;
import com.cyclone.dineflow.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemResponseDto> getOrderItems(String orderId) {
        List<OrderItemResponseDto> allOrders = orderItemRepository.findAllByOrderId(orderId).stream().map((orderItem)-> OrderItemResponseDtoMapper.toDto(orderItem)).toList();
        return allOrders;
    }

    @Override
    public String updateOrderItemsStatus(String orderItemId, String status) {
        OrderItem foundOrderItem = orderItemRepository.findById(orderItemId).orElseThrow(()-> new RuntimeException("Order Item Not Found"));
        foundOrderItem.setStatus(OrderItemStatus.valueOf(status));
        orderItemRepository.save(foundOrderItem);
        return "Order Item Status Updated";
    }
}
