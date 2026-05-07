package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.MenuItemVariantRequestDto;
import com.cyclone.dineflow.dto.responsedto.MenuItemVariantResponseDto;
import com.cyclone.dineflow.service.MenuItemVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 07-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MenuItemVariantController {
    private final MenuItemVariantService  menuItemVariantService;

    @PostMapping("/items/{itemId}/variants")
    public ResponseEntity<MenuItemVariantResponseDto> addVariant(@RequestBody MenuItemVariantRequestDto menuItemVariantRequestDto, @PathVariable String itemId) {
        return ResponseEntity.ok(menuItemVariantService.addVariant(menuItemVariantRequestDto,itemId));
    }

    @GetMapping("/items/{itemId}/variants")
    public ResponseEntity<List<MenuItemVariantResponseDto>> getAllVariantsOfAMenuItem(@PathVariable String itemId) {
        return ResponseEntity.ok(menuItemVariantService.getAllVariantsOfAMenuItem(itemId));
    }

    @PutMapping("/variants/{id}")
    public ResponseEntity<String> updateVariant(@PathVariable String id, @RequestBody MenuItemVariantRequestDto menuItemVariantRequestDto) {
        return ResponseEntity.ok(menuItemVariantService.updateVariant(id,menuItemVariantRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVariant(@PathVariable String id) {
        return  ResponseEntity.ok(menuItemVariantService.deleteVariant(id));
    }
}
