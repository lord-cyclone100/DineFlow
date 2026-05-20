package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.CategoryRequestDto;
import com.cyclone.dineflow.dto.responsedto.CategoryResponseDto;
import com.cyclone.dineflow.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/branches/{branchId}/categories")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<CategoryResponseDto> createCategory(@PathVariable String branchId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.createCategory(branchId, categoryRequestDto));
    }

    @GetMapping("/branches/{branchId}/categories")
    public ResponseEntity<List<CategoryResponseDto>> viewAllCategory(@PathVariable String branchId) {
        return ResponseEntity.ok(categoryService.viewAllCategory(branchId));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> getParticularCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.getParticularCategory(id));
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> updateParticularCategory(@RequestBody CategoryRequestDto categoryRequestDto, @PathVariable String id) {
        return ResponseEntity.ok(categoryService.updateParticularCategory(categoryRequestDto, id));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteParticularCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.deleteParticularCategory(id));
    }

    @PatchMapping("/categories/{id}/toggle-active")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> toggleActiveParticularCategory(@PathVariable String id, @RequestParam String status) {
        return ResponseEntity.ok(categoryService.toggleActiveParticularCategory(id, status));
    }

}
