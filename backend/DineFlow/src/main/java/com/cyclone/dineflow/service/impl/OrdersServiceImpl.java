package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.OrderItemRequestDto;
import com.cyclone.dineflow.dto.requestdto.OrdersRequestDto;
import com.cyclone.dineflow.dto.responsedto.OrdersResponseDto;
import com.cyclone.dineflow.dtomapper.OrdersResponseDtoMapper;
import com.cyclone.dineflow.entity.*;
import com.cyclone.dineflow.entity.data.OrderStatus;
import com.cyclone.dineflow.repository.*;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemVariantRepository menuItemVariantRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrdersResponseDto placeOrder(UserPrincipal userPrincipal, OrdersRequestDto ordersRequestDto) {
        String userId = userPrincipal.userId();
        User foundUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Branch foundBranch = branchRepository.findById(ordersRequestDto.branchId()).orElseThrow(()-> new RuntimeException("Branch not found"));
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(ordersRequestDto.tableId()).orElseThrow(()-> new RuntimeException("Table does not exist"));

        int totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestDto itemDto : ordersRequestDto.orderItems()){
            MenuItem menuItem = menuItemRepository.findById(itemDto.menuItemId()).orElseThrow(()-> new RuntimeException("Item does not exist"));
            MenuItemVariant variant = menuItemVariantRepository.findById(itemDto.variantId()).orElseThrow(()-> new RuntimeException("Variant does not exist"));
            int unitPrice = menuItem.getBasePrice() + variant.getExtraPrice();
            int itemTotal = (unitPrice) * itemDto.quantity();
            totalAmount += itemTotal;

            OrderItem newOrderItem = OrderItem.builder()
                    .menuItem(menuItem)
                    .variant(variant)
                    .quantity(itemDto.quantity())
                    .unitPrice(unitPrice)
                    .notes(itemDto.notes())
                    .build();
            orderItems.add(newOrderItem);
        }

        int finalAmount =  (totalAmount - ordersRequestDto.discount()) + ordersRequestDto.tax();

        Orders newOrder = Orders.builder()
                .user(foundUser)
                .branch(foundBranch)
                .table(foundRestaurantTable)
                .orderType(ordersRequestDto.orderType())
                .totalAmount(totalAmount)
                .tax(ordersRequestDto.tax())
                .discount(ordersRequestDto.discount())
                .finalAmount(finalAmount)
                .specialInstructions(ordersRequestDto.specialInstructions())
                .deliveryAddress(ordersRequestDto.deliveryAddress())
                .build();

        ordersRepository.save(newOrder);

        for (OrderItem orderItem : orderItems){
            orderItem.setOrder(newOrder);
            orderItemRepository.save(orderItem);
        }
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
