package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.RestaurantTableRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantTableResponseDto;
import com.cyclone.dineflow.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @PostMapping("/branches/{branchId}/tables")
    public ResponseEntity<RestaurantTableResponseDto> createTable(@PathVariable String branchId, @RequestBody RestaurantTableRequestDto restaurantTableRequestDto) {
        return ResponseEntity.ok(restaurantTableService.createTable(branchId,restaurantTableRequestDto));
    }

    @GetMapping("/branches/{branchId}/tables")
    public ResponseEntity<List<RestaurantTableResponseDto>> getAllTablesOfABranch(@PathVariable String branchId, @RequestBody RestaurantTableRequestDto restaurantTableRequestDto) {
        return ResponseEntity.ok(restaurantTableService.getAllTablesOfABranch(branchId,restaurantTableRequestDto));
    }

    @GetMapping("/branches/{branchId}/tables/available")
    public ResponseEntity<List<RestaurantTableResponseDto>> getAllAvailableTablesOfABranch(@PathVariable String branchId, @RequestBody RestaurantTableRequestDto restaurantTableRequestDto) {
        return ResponseEntity.ok(restaurantTableService.getAllAvailableTablesOfABranch(branchId,restaurantTableRequestDto));
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<RestaurantTableResponseDto> getParticularTable(@PathVariable String tableId) {
        return ResponseEntity.ok(restaurantTableService.getParticularTable(tableId));
    }

    @PutMapping("/tables/{id}")
    public ResponseEntity<String> updateParticularTable(@PathVariable String tableId, @RequestBody RestaurantTableRequestDto restaurantTableRequestDto) {
        return ResponseEntity.ok(restaurantTableService.updateParticularTable(tableId, restaurantTableRequestDto));
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<String> deleteParticularTable(@PathVariable String tableId) {
        return ResponseEntity.ok(restaurantTableService.deleteParticularTable(tableId));
    }

    @PatchMapping("/tables/{id}/toggle-active")
    public ResponseEntity<String> toggleParticularTable(@PathVariable String tableId) {
        return ResponseEntity.ok(restaurantTableService.toggleParticularTable(tableId));
    }
}
