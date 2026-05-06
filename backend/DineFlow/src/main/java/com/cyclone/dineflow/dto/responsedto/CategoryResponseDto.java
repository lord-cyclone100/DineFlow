package com.cyclone.dineflow.dto.responsedto;

import com.cyclone.dineflow.entity.data.CategoryActive;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.minidev.json.annotate.JsonIgnore;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 06-05-2026
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryResponseDto(
        String name,
        String description,
        String restaurantName,
        String branch,
        CategoryActive categoryActive,
        String message
)
{}