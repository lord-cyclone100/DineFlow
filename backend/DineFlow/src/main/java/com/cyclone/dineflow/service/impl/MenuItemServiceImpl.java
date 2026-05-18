package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.MenuItemRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemResponseDto;
import com.cyclone.dineflow.dtomapper.MenuItemResponseDtoMapper;
import com.cyclone.dineflow.entity.Category;
import com.cyclone.dineflow.entity.MenuItem;
import com.cyclone.dineflow.entity.data.MenuAvailability;
import com.cyclone.dineflow.entity.data.MenuCategory;
import com.cyclone.dineflow.repository.BranchRepository;
import com.cyclone.dineflow.repository.CategoryRepository;
import com.cyclone.dineflow.repository.MenuItemRepository;
import com.cyclone.dineflow.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 06-05-2026
 */
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final BranchRepository branchRepository;

    @Override
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto, String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<MenuItem> foundMenuItem = menuItemRepository.findByName(menuItemRequestDto.name());

        if(category.isPresent() && foundMenuItem.isPresent()) {
            throw new RuntimeException("Menu Item already exists");
        }
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemRequestDto.name())
                .description(menuItemRequestDto.description())
                .category(category.get())
                .menuCategory(menuItemRequestDto.menuCategory())
                .basePrice(menuItemRequestDto.basePrice())
                .preparationTimeInMinutes(menuItemRequestDto.preparationTimeInMinutes())
                .build();
        menuItemRepository.save(menuItem);
        return MenuItemResponseDtoMapper.toDto(menuItem,"MenuItem created");
    }

    @Override
    public List<MenuItemResponseDto> getMenuItemsOfBranch(String branchId) {
        List<MenuItemResponseDto> menuItemsOnBranch = menuItemRepository.findAll().stream().filter((menuItem)->menuItem.getCategory().getBranch().getId().equals(branchId)).map((menuItem)->MenuItemResponseDtoMapper.toDto(menuItem,null)).toList();
        return menuItemsOnBranch;
    }

    @Override
    public List<MenuItemResponseDto> getMenuItemsOfCategory(String categoryId) {
        List<MenuItemResponseDto> menuItemsOnCategory = menuItemRepository.findAll().stream().filter((menuItem)->menuItem.getCategory().getId().equals(categoryId)).map((menuItem)->MenuItemResponseDtoMapper.toDto(menuItem,null)).toList();
        return menuItemsOnCategory;
    }

    @Override
    public MenuItemResponseDto getParticularMenuItem(String menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("Menu Item Not Found"));
        return MenuItemResponseDtoMapper.toDto(menuItem,null);
    }

    @Override
    public String updateParticularMenuItem(MenuItemRequestDto menuItemRequestDto, String menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("Menu Item Not Found"));
        menuItem.setName(menuItemRequestDto.name());
        menuItem.setDescription(menuItemRequestDto.description());
        menuItem.setBasePrice(menuItemRequestDto.basePrice());
        menuItem.setMenuCategory(menuItemRequestDto.menuCategory());
        menuItem.setPreparationTimeInMinutes(menuItemRequestDto.preparationTimeInMinutes());
        menuItemRepository.save(menuItem);
        return "Menu Item Updated";
    }

    @Override
    public String deleteMenuItem(String menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("Menu Item Not Found"));
        menuItemRepository.delete(menuItem);
        return "Menu Item Deleted";
    }

    @Override
    public String toggleMenuItemAvailability(String menuItemId, String availability) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("Menu Item Not Found"));
        menuItem.setMenuAvailability(MenuAvailability.valueOf(availability));
        menuItemRepository.save(menuItem);
        return "Menu Item availability updated";
    }
}
