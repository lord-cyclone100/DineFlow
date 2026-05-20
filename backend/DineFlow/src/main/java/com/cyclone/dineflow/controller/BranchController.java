package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.BranchRequestDto;
import com.cyclone.dineflow.dto.responsedto.BranchResponseDto;
import com.cyclone.dineflow.service.BranchService;
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
 * @since 05-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/restaurants/{restaurantId}/branches")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponseDto> createRestaurantBranch(@RequestBody BranchRequestDto branchRequestDto, @PathVariable String restaurantId) {
        return ResponseEntity.ok(branchService.createRestaurantBranch(branchRequestDto,restaurantId));
    }

    @GetMapping("/restaurants/{restaurantId}/branches")
    public ResponseEntity<List<BranchResponseDto>> getRestaurantBranches(@PathVariable String restaurantId) {
        return ResponseEntity.ok(branchService.getRestaurantBranches(restaurantId));
    }

    @GetMapping("/branches/{id}")
    public ResponseEntity<BranchResponseDto> getParticularBranchDetails(@PathVariable String id) {
        return ResponseEntity.ok(branchService.getParticularBranchDetails(id));
    }

    @PutMapping("/branches/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> updateParticularBranchDetails(@RequestBody BranchRequestDto branch, @PathVariable String id) {
        return ResponseEntity.ok(branchService.updateParticularBranchDetails(branch, id));
    }

    @DeleteMapping("/branches/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteParticularBranch(@PathVariable String id) {
        return ResponseEntity.ok(branchService.deleteParticularBranch(id));
    }
}
