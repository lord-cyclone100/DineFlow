package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.MenuItemVariantRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemVariantResponseDto;
import com.cyclone.dineflow.dtomapper.MenuItemVariantResponseDtoMapper;
import com.cyclone.dineflow.entity.MenuItem;
import com.cyclone.dineflow.entity.MenuItemVariant;
import com.cyclone.dineflow.exceptions.custom.MenuItemVariantAlreadyExistsException;
import com.cyclone.dineflow.exceptions.custom.MenuItemVariantNotFoundException;
import com.cyclone.dineflow.repository.MenuItemRepository;
import com.cyclone.dineflow.repository.MenuItemVariantRepository;
import com.cyclone.dineflow.service.MenuItemVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 07-05-2026
 */
@Service
@RequiredArgsConstructor
public class MenuItemVariantServiceImpl implements MenuItemVariantService {

    private final MenuItemVariantRepository menuItemVariantRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItemVariantResponseDto addVariant(MenuItemVariantRequestDto menuItemVariantRequestDto, String itemId) {
        List<MenuItemVariant> foundVariant = menuItemVariantRepository.findAllByMenuItemId(itemId);

        for(MenuItemVariant menuItemVariant : foundVariant){
            if(menuItemVariant.getName().equals(menuItemVariantRequestDto.name())){
                throw new MenuItemVariantAlreadyExistsException(menuItemVariant.getName(), menuItemVariant.getMenuItem().getName());
            }
        }

        Optional<MenuItem> foundMenuItem = menuItemRepository.findById(itemId);
        MenuItemVariant menuItemVariant = MenuItemVariant.builder()
                .name(menuItemVariantRequestDto.name())
                .menuItem(foundMenuItem.get())
                .extraPrice(menuItemVariantRequestDto.extraPrice())
                .build();
        menuItemVariantRepository.save(menuItemVariant);
        return MenuItemVariantResponseDtoMapper.toDto(menuItemVariant,"Variant added");
    }

    @Override
    public List<MenuItemVariantResponseDto> getAllVariantsOfAMenuItem(String itemId) {
        List<MenuItemVariantResponseDto> itemVariants = menuItemVariantRepository.findAll().stream().filter((variant)->variant.getMenuItem().getId().equals(itemId)).map((variant)->MenuItemVariantResponseDtoMapper.toDto(variant,null)).toList();
        return itemVariants;
    }

    @Override
    public String updateVariant(String variantId, MenuItemVariantRequestDto menuItemVariantRequestDto) {
        MenuItemVariant foundVariant = menuItemVariantRepository.findById(variantId).orElseThrow(() -> new MenuItemVariantNotFoundException(variantId));
        foundVariant.setName(menuItemVariantRequestDto.name());
        foundVariant.setExtraPrice(menuItemVariantRequestDto.extraPrice());
        menuItemVariantRepository.save(foundVariant);
        return "Variant updated successfully";
    }

    @Override
    public String deleteVariant(String variantId) {
        MenuItemVariant foundVariant = menuItemVariantRepository.findById(variantId).orElseThrow(() -> new MenuItemVariantNotFoundException(variantId));
        menuItemVariantRepository.delete(foundVariant);
        return "Variant deleted successfully";
    }
}
