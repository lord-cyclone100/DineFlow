package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.BranchResponseDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantResponseDto;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 05-05-2026
 */

public class BranchResponseDtoMapper {
    public static BranchResponseDto toDto(Branch branch, String message) {
        return new BranchResponseDto(
                branch.getName(),
                branch.getCity(),
                branch.getAddress(),
                message
        );
    }
}