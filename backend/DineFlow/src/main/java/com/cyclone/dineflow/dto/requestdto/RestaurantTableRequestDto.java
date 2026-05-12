package com.cyclone.dineflow.dto.requestdto;

import com.cyclone.dineflow.entity.data.TableLocation;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
public record RestaurantTableRequestDto(
        String tableNumber,
        Integer capacity,
        TableLocation tableLocation
)
{}
