package com.cyclone.dineflow.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 07-05-2026
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MenuItemVariantResponseDto (
        String menuItemName,
        String variantName,
        int extraPrice,
        String message
)
{}
