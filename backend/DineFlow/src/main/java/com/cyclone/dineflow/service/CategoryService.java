package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.CategoryRequestDto;
import com.cyclone.dineflow.dto.responsedto.CategoryResponseDto;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 06-05-2026
 */
public interface CategoryService {
    CategoryResponseDto createCategory(String branchId, CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> viewAllCategory(String branchId);

    CategoryResponseDto getParticularCategory(String id);

    String updateParticularCategory(CategoryRequestDto categoryRequestDto, String id);

    String deleteParticularCategory(String id);

    String toggleActiveParticularCategory(String id, String status);
}
