package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.MenuItemVariantRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemVariantResponseDto;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 07-05-2026
 */
public interface MenuItemVariantService {
    MenuItemVariantResponseDto addVariant(MenuItemVariantRequestDto menuItemVariantRequestDto, String itemId);

    List<MenuItemVariantResponseDto> getAllVariantsOfAMenuItem(String itemId);

    String updateVariant(String id, MenuItemVariantRequestDto menuItemVariantRequestDto);

    String deleteVariant(String id);
}
