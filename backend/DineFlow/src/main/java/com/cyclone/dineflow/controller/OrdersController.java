package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.OrdersRequestDto;
import com.cyclone.dineflow.dto.responsedto.OrdersResponseDto;
import com.cyclone.dineflow.security.UserPrincipal;
import com.cyclone.dineflow.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/orders")
    public ResponseEntity<OrdersResponseDto> placeOrder(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody OrdersRequestDto ordersRequestDto){
        return ResponseEntity.ok(ordersService.placeOrder(userPrincipal, ordersRequestDto));
    }

    @GetMapping("/orders/my")
    public ResponseEntity<List<OrdersResponseDto>> viewMyOrders(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(ordersService.viewMyOrders(userPrincipal));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrdersResponseDto> viewOrderById(@PathVariable String orderId){
        return ResponseEntity.ok(ordersService.viewOrderById(orderId));
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId){
        return ResponseEntity.ok(ordersService.cancelOrder(orderId));
    }

    @GetMapping("/orders/{orderId}/track")
    public ResponseEntity<OrdersResponseDto> trackOrder(@PathVariable String orderId){
        return ResponseEntity.ok(ordersService.trackOrder(orderId));
    }

    @GetMapping("/branches/{branchId}/orders")
    public ResponseEntity<List<OrdersResponseDto>> getAllOrdersOfABranch(@PathVariable String branchId){
        return ResponseEntity.ok(ordersService.getAllOrdersOfABranch(branchId));
    }

    @GetMapping("/branches/{branchId}/orders/active")
    public ResponseEntity<List<OrdersResponseDto>> getAllActiveOrdersOfABranch(@PathVariable String branchId){
        return ResponseEntity.ok(ordersService.getAllActiveOrdersOfABranch(branchId));
    }

    @GetMapping("/branches/{branchId}/orders/today")
    public ResponseEntity<List<OrdersResponseDto>> getAllTodaysOrdersOfABranch(@PathVariable String branchId){
        return ResponseEntity.ok(ordersService.getAllTodaysOrdersOfABranch(branchId));
    }

    @PatchMapping("/orders/{orderId}/confirm")
    public ResponseEntity<String> confirmOrder(@PathVariable String orderId){
        return ResponseEntity.ok(ordersService.confirmOrder(orderId));
    }

    @PatchMapping("/orders/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @RequestParam String status){
        return ResponseEntity.ok(ordersService.updateOrderStatus(orderId, status));
    }
}
