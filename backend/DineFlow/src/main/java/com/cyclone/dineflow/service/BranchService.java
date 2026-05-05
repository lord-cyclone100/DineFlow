package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.BranchRequestDto;
import com.cyclone.dineflow.dto.responsedto.BranchResponseDto;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 05-05-2026
 */
public interface BranchService {
    BranchResponseDto createRestaurantBranch(BranchRequestDto branchRequestDto, String restaurantId);

    List<BranchResponseDto> getRestaurantBranches(String restaurantId);

    BranchResponseDto getParticularBranchDetails(String id);

    String updateParticularBranchDetails(BranchRequestDto branch, String id);

    String deleteParticularBranch(String id);
}
