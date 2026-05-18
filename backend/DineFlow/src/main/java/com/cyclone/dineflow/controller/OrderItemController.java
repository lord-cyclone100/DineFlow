package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.responsedto.OrderItemResponseDto;
import com.cyclone.dineflow.entity.data.OrderItemStatus;
import com.cyclone.dineflow.service.OrderItemService;
import com.cyclone.dineflow.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/orders/{orderId}/items")
    public ResponseEntity<List<OrderItemResponseDto>> getOrderItems(@PathVariable String orderId){
        return ResponseEntity.ok(orderItemService.getOrderItems(orderId));
    }

    @PatchMapping("/order-items/{orderItemId}/status")
    public ResponseEntity<String> updateOrderItemStatus(@PathVariable String orderItemId, @RequestParam String status){
        return ResponseEntity.ok(orderItemService.updateOrderItemsStatus(orderItemId, status));
    }
}
