package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.MenuItemVariantRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemVariantResponseDto;
import com.cyclone.dineflow.dtomapper.MenuItemVariantResponseDtoMapper;
import com.cyclone.dineflow.entity.MenuItem;
import com.cyclone.dineflow.entity.MenuItemVariant;
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
        Optional<MenuItemVariant> foundVariant = menuItemVariantRepository.findByName(menuItemVariantRequestDto.name());
        Optional<MenuItem> foundMenuItem = menuItemRepository.findById(itemId);

        if(foundVariant.isPresent() && foundMenuItem.isPresent()){
            throw new RuntimeException("Variant already exists");
        }

        MenuItemVariant menuItemVariant = MenuItemVariant.builder()
                .name(menuItemVariantRequestDto.name())
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
        MenuItemVariant foundVariant = menuItemVariantRepository.findById(variantId).orElseThrow(() -> new RuntimeException("Variant not found"));
        foundVariant.setName(menuItemVariantRequestDto.name());
        foundVariant.setExtraPrice(menuItemVariantRequestDto.extraPrice());
        menuItemVariantRepository.save(foundVariant);
        return "Variant updated successfully";
    }

    @Override
    public String deleteVariant(String variantId) {
        MenuItemVariant foundVariant = menuItemVariantRepository.findById(variantId).orElseThrow(() -> new RuntimeException("Variant not found"));
        menuItemVariantRepository.delete(foundVariant);
        return "Variant deleted successfully";
    }
}
