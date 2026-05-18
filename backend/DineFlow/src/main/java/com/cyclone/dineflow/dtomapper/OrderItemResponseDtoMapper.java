package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.OrderItemResponseDto;
import com.cyclone.dineflow.entity.OrderItem;

public class OrderItemResponseDtoMapper {
    public static OrderItemResponseDto toDto(OrderItem orderItem){
        return new  OrderItemResponseDto(
                orderItem.getOrder().getId(),
                orderItem.getMenuItem().getName(),
                orderItem.getVariant().getName(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getQuantity() *  orderItem.getUnitPrice(),
                orderItem.getStatus(),
                orderItem.getNotes()
        );
    }
}
