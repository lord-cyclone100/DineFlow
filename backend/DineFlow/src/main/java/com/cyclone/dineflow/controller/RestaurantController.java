package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.RestaurantRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantResponseDto;
import com.cyclone.dineflow.entity.Restaurant;
import com.cyclone.dineflow.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 03-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody RestaurantRequestDto restaurant) {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurant));
    }

    @GetMapping("/restaurants")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantResponseDto> getParticularRestaurant(@PathVariable String id) {
        return ResponseEntity.ok(restaurantService.getParticularRestaurant(id));
    }

    @PutMapping("/restaurants/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateRestaurant(@PathVariable String id, @RequestBody RestaurantRequestDto restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
    }

    @DeleteMapping("/restaurants/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRestaurant(@PathVariable String id) {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(id));
    }
}
