package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.OrdersRequestDto;
import com.cyclone.dineflow.dto.responsedto.OrdersResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;

import java.util.List;

public interface OrdersService {
    OrdersResponseDto placeOrder(UserPrincipal userPrincipal, OrdersRequestDto ordersRequestDto);

    List<OrdersResponseDto> viewMyOrders(UserPrincipal userPrincipal);

    OrdersResponseDto viewOrderById(String orderId);

    String cancelOrder(String orderId);

    OrdersResponseDto trackOrder(String orderId);

    List<OrdersResponseDto> getAllOrdersOfABranch(String branchId);

    List<OrdersResponseDto> getAllActiveOrdersOfABranch(String branchId);

    List<OrdersResponseDto> getAllTodaysOrdersOfABranch(String branchId);

    String confirmOrder(String orderId);

    String updateOrderStatus(String orderId, String status);
}
