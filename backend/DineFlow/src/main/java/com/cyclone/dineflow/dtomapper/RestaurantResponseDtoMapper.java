package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.RegisterResponseDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantResponseDto;
import com.cyclone.dineflow.entity.Restaurant;
import com.cyclone.dineflow.entity.User;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 03-05-2026
 */
public class RestaurantResponseDtoMapper {
    public static RestaurantResponseDto toDto(Restaurant restaurant, String message) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCuisineType(),
                message
        );
    }
}
