package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.MenuItemRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemResponseDto;
import com.cyclone.dineflow.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 06-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping("/categories/{categoryId}/items")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<MenuItemResponseDto> createMenuItem(@RequestBody MenuItemRequestDto menuItemRequestDto, @PathVariable String categoryId) {
        return ResponseEntity.ok(menuItemService.createMenuItem(menuItemRequestDto, categoryId));
    }

    @GetMapping("/branches/{branchId}/items")
    public ResponseEntity<List<MenuItemResponseDto>> getMenuItemsOfBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsOfBranch(branchId));
    }

    @GetMapping("/categories/{categoryId}/items")
    public ResponseEntity<List<MenuItemResponseDto>> getMenuItemsOfCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsOfCategory(categoryId));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItemResponseDto> getParticularMenuItem(@PathVariable String menuItemId) {
        return ResponseEntity.ok(menuItemService.getParticularMenuItem(menuItemId));
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> updateParticularMenuItem(@RequestBody MenuItemRequestDto menuItemRequestDto, @PathVariable String menuItemId) {
        return ResponseEntity.ok(menuItemService.updateParticularMenuItem(menuItemRequestDto, menuItemId));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteMenuItem(@PathVariable String menuItemId) {
        return ResponseEntity.ok(menuItemService.deleteMenuItem(menuItemId));
    }

    @PatchMapping("/items/{id}/toggle-available")
    @PreAuthorize("hasAnyRole('MANAGER','STAFF')")
    public ResponseEntity<String> toggleMenuItemAvailability(@PathVariable String id, @RequestParam String availability) {
        return ResponseEntity.ok(menuItemService.toggleMenuItemAvailability(id, availability));
    }
}
