package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.RestaurantRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantResponseDto;
import com.cyclone.dineflow.entity.Restaurant;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 03-05-2026
 */
public interface RestaurantService {
    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurant);

    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto getParticularRestaurant(String id);

    String updateRestaurant(String id, RestaurantRequestDto restaurant);

    String deleteRestaurant(String id);
}
