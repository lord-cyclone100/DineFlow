package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.RestaurantTableResponseDto;
import com.cyclone.dineflow.entity.RestaurantTable;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
public class RestaurantTableResponseDtoMapper {
    public static RestaurantTableResponseDto toDto(RestaurantTable restaurantTable, String message) {
        return new RestaurantTableResponseDto(
                restaurantTable.getTableNumber(),
                restaurantTable.getCapacity(),
                restaurantTable.getLocation(),
                restaurantTable.getBranch().getName(),
                restaurantTable.isActive(),
                message
        );
    }
}
