package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.BranchRequestDto;
import com.cyclone.dineflow.dto.responsedto.BranchResponseDto;
import com.cyclone.dineflow.dtomapper.BranchResponseDtoMapper;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.Restaurant;
import com.cyclone.dineflow.exceptions.custom.BranchAlreadyExistsException;
import com.cyclone.dineflow.exceptions.custom.BranchNotFoundException;
import com.cyclone.dineflow.repository.BranchRepository;
import com.cyclone.dineflow.repository.RestaurantRepository;
import com.cyclone.dineflow.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 05-05-2026
 */
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public BranchResponseDto createRestaurantBranch(BranchRequestDto branchRequestDto, String restaurantId) {

        List<Branch> requiredBranches = branchRepository.findAllByRestaurantId(restaurantId);

        for(Branch branch:requiredBranches){
            if(branch.getName().equals(branchRequestDto.name())){
                throw new BranchAlreadyExistsException(branchRequestDto.name(),branch.getRestaurant().getName());
            }
        }

        Optional<Restaurant>existingRestaurant = restaurantRepository.findById(restaurantId);
        Branch branch = Branch.builder()
                .restaurant(existingRestaurant.get())
                .name(branchRequestDto.name())
                .city(branchRequestDto.city())
                .address(branchRequestDto.address())
                .phoneNumber(branchRequestDto.phoneNumber())
                .openTime(branchRequestDto.openTime())
                .closeTime(branchRequestDto.closeTime())
                .build();
        branchRepository.save(branch);
        return BranchResponseDtoMapper.toDto(branch,"Branch created successfully");
    }

    @Override
    public List<BranchResponseDto> getRestaurantBranches(String restaurantId) {
        List<BranchResponseDto> branchList =  branchRepository.findAll().stream().filter((branch -> branch.getRestaurant().getId().equals(restaurantId))).map((branch)->BranchResponseDtoMapper.toDto(branch,null)).toList();
        return branchList;
    }

    @Override
    public BranchResponseDto getParticularBranchDetails(String id) {
        Branch foundBranch = branchRepository.findById(id).orElseThrow(()-> new BranchNotFoundException(id));
        return BranchResponseDtoMapper.toDto(foundBranch,null);
    }

    @Override
    public String updateParticularBranchDetails(BranchRequestDto branch, String id) {
        Branch foundBranch = branchRepository.findById(id).orElseThrow(()-> new BranchNotFoundException(id));
        foundBranch.setName(branch.name());
        foundBranch.setCity(branch.city());
        foundBranch.setOpenTime(branch.openTime());
        foundBranch.setCloseTime(branch.closeTime());
        foundBranch.setAddress(branch.address());
        foundBranch.setPhoneNumber(branch.phoneNumber());

        branchRepository.save(foundBranch);
        return "Branch updated successfully";
    }

    @Override
    public String deleteParticularBranch(String id) {
        Branch foundBranch = branchRepository.findById(id).orElseThrow(()-> new BranchNotFoundException(id));
        branchRepository.delete(foundBranch);
        return "Branch deleted successfully";
    }
}
