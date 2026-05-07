package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.MenuItemResponseDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemVariantResponseDto;
import com.cyclone.dineflow.entity.MenuItem;
import com.cyclone.dineflow.entity.MenuItemVariant;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 07-05-2026
 */
public class MenuItemVariantResponseDtoMapper {
    public static MenuItemVariantResponseDto toDto(MenuItemVariant variant, String message) {
        return new MenuItemVariantResponseDto(
                variant.getMenuItem().getName(),
                variant.getName(),
                variant.getExtraPrice(),
                message
        );
    }
}
