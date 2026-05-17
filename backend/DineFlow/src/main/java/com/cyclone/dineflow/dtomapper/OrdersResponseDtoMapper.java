package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.OrdersResponseDto;
import com.cyclone.dineflow.entity.Orders;

public class OrdersResponseDtoMapper {
    public static OrdersResponseDto toDto(Orders order, String message){
        return new OrdersResponseDto(
                order.getUser().getName(),
                order.getBranch().getRestaurant().getName(),
                order.getBranch().getName(),
                order.getTable().getTableNumber(),
                order.getOrderType(),
                order.getTotalAmount(),
                order.getDiscount(),
                order.getTax(),
                order.getFinalAmount(),
                order.getSpecialInstructions(),
                message
        );
    }
}
