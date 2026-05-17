package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.OrdersRequestDto;
import com.cyclone.dineflow.dto.responsedto.OrdersResponseDto;
import com.cyclone.dineflow.dtomapper.OrdersResponseDtoMapper;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Orders;
import com.cyclone.dineflow.entity.RestaurantTable;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.OrderStatus;
import com.cyclone.dineflow.repository.BranchRepository;
import com.cyclone.dineflow.repository.OrdersRepository;
import com.cyclone.dineflow.repository.RestaurantTableRepository;
import com.cyclone.dineflow.repository.UserRepository;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    @Override
    public OrdersResponseDto placeOrder(UserPrincipal userPrincipal, OrdersRequestDto ordersRequestDto) {
        String userId = userPrincipal.userId();
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Branch foundBranch = branchRepository.findById(ordersRequestDto.branchId()).orElseThrow(()-> new RuntimeException("Branch not found"));
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(ordersRequestDto.tableId()).orElseThrow(()-> new RuntimeException("Table does not exist"));

        Orders newOrder = Orders.builder()
                .user(foundUser)
                .branch(foundBranch)
                .table(foundRestaurantTable)
                .orderType(ordersRequestDto.orderType())
                .totalAmount(ordersRequestDto.totalAmount())
                .tax(ordersRequestDto.tax())
                .discount(ordersRequestDto.discount())
                .finalAmount((ordersRequestDto.totalAmount() - ordersRequestDto.discount()) + ordersRequestDto.tax())
                .specialInstructions(ordersRequestDto.specialInstructions())
                .deliveryAddress(ordersRequestDto.deliveryAddress())
                .build();

        ordersRepository.save(newOrder);
        return OrdersResponseDtoMapper.toDto(newOrder,"Order created");
    }

    @Override
    public List<OrdersResponseDto> viewMyOrders(UserPrincipal userPrincipal) {
        String userId = userPrincipal.userId();
        List<OrdersResponseDto> myOrderList = ordersRepository.findAllByUserId(userId).stream().map((order)->OrdersResponseDtoMapper.toDto(order,null)).toList();
        return myOrderList;
    }

    @Override
    public OrdersResponseDto viewOrderById(String orderId) {
        Orders foundOrder = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        return OrdersResponseDtoMapper.toDto(foundOrder,null);
    }

    @Override
    public String cancelOrder(String orderId) {
        Orders foundOrder = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        foundOrder.setStatus(OrderStatus.CANCELLED);
        ordersRepository.save(foundOrder);
        return "Order cancelled";
    }

    @Override
    public OrdersResponseDto trackOrder(String orderId) {
        Orders foundOrder = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        return OrdersResponseDtoMapper.toDto(foundOrder,null);
    }

    @Override
    public List<OrdersResponseDto> getAllOrdersOfABranch(String branchId) {
        List<OrdersResponseDto> branchOrders = ordersRepository.findAllByBranchId(branchId).stream().map((order)-> OrdersResponseDtoMapper.toDto(order,null)).toList();
        return branchOrders;
    }

    @Override
    public List<OrdersResponseDto> getAllActiveOrdersOfABranch(String branchId) {
        List<OrdersResponseDto> activeBranchOrders = ordersRepository.findAllByBranchId(branchId).stream().filter((order)-> !order.getStatus().equals("COMPLETED") || !order.getStatus().equals("READY") || !order.getStatus().equals("CANCELLED")).map((order)-> OrdersResponseDtoMapper.toDto(order,null)).toList();
        return activeBranchOrders;
    }

    @Override
    public List<OrdersResponseDto> getAllTodaysOrdersOfABranch(String branchId) {
        return List.of();
    }

    @Override
    public String confirmOrder(String orderId) {
        Orders foundOrder = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        foundOrder.setStatus(OrderStatus.CONFIRMED);
        ordersRepository.save(foundOrder);
        return "Order confirmed";
    }

    @Override
    public String updateOrderStatus(String orderId, String status) {
        Orders foundOrder = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        foundOrder.setStatus(OrderStatus.valueOf(status));
        ordersRepository.save(foundOrder);
        return "Order status updated";
    }
}
