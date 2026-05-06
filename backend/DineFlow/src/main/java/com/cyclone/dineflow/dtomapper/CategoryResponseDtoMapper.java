package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.BranchResponseDto;
import com.cyclone.dineflow.dto.responsedto.CategoryResponseDto;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Category;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 06-05-2026
 */
public class CategoryResponseDtoMapper {
    public static CategoryResponseDto toDto(Category category, String message) {
        return new CategoryResponseDto(
                category.getName(),
                category.getDescription(),
                category.getBranch().getRestaurant().getName(),
                category.getBranch().getName(),
                category.getIsActive(),
                message
        );
    }
}
