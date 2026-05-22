package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.CategoryRequestDto;
import com.cyclone.dineflow.dto.responsedto.CategoryResponseDto;
import com.cyclone.dineflow.dtomapper.CategoryResponseDtoMapper;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Category;
import com.cyclone.dineflow.entity.Restaurant;
import com.cyclone.dineflow.entity.data.CategoryActive;
import com.cyclone.dineflow.exceptions.custom.CategoryAlreadyExistsException;
import com.cyclone.dineflow.exceptions.custom.CategoryNotFoundException;
import com.cyclone.dineflow.repository.BranchRepository;
import com.cyclone.dineflow.repository.CategoryRepository;
import com.cyclone.dineflow.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BranchRepository branchRepository;

    @Override
    public CategoryResponseDto createCategory(String branchId, CategoryRequestDto categoryRequestDto) {

        List<Category> foundCategories = categoryRepository.findAllByBranchId(branchId);

        for(Category category : foundCategories){
            if(category.getName().equals(categoryRequestDto.name())){
                throw new CategoryAlreadyExistsException(categoryRequestDto.name(), category.getBranch().getName());
            }
        }

        Optional<Branch> existingBranch = branchRepository.findById(branchId);
        Category category = Category.builder()
                .branch(existingBranch.get())
                .name(categoryRequestDto.name())
                .description(categoryRequestDto.description())
                .build();
        categoryRepository.save(category);
        return CategoryResponseDtoMapper.toDto(category,"Category created successfully");
    }

    @Override
    public List<CategoryResponseDto> viewAllCategory(String branchId) {
        List<CategoryResponseDto> categoryList = categoryRepository.findAll().stream().filter(category -> category.getBranch().getId().equals(branchId)).map(category -> CategoryResponseDtoMapper.toDto(category,null)).toList();
        return categoryList;
    }

    @Override
    public CategoryResponseDto getParticularCategory(String id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        return CategoryResponseDtoMapper.toDto(existingCategory,null);
    }

    @Override
    public String updateParticularCategory(CategoryRequestDto categoryRequestDto, String id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        existingCategory.setName(categoryRequestDto.name());
        existingCategory.setDescription(categoryRequestDto.description());
        categoryRepository.save(existingCategory);
        return "Category updated successfully";
    }

    @Override
    public String deleteParticularCategory(String id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        categoryRepository.delete(existingCategory);
        return "Category deleted successfully";
    }

    @Override
    public String toggleActiveParticularCategory(String id, String status) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        existingCategory.setIsActive(CategoryActive.valueOf(status));
        categoryRepository.save(existingCategory);
        return "Status Changed";
    }
}
