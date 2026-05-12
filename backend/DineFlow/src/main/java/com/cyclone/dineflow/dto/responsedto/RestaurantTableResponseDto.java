package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.TableLocation;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestaurantTableResponseDto(
        String tableNumber,
        Integer capacity,
        TableLocation tableLocation,
        String branchName,
        Boolean availability,
        String message
)
{}
