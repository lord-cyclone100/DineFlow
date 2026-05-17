package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.RestaurantTableRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantTableResponseDto;
import com.cyclone.dineflow.dtomapper.RestaurantResponseDtoMapper;
import com.cyclone.dineflow.dtomapper.RestaurantTableResponseDtoMapper;
import com.cyclone.dineflow.entity.Branch;
import com.cyclone.dineflow.entity.RestaurantTable;
import com.cyclone.dineflow.repository.BranchRepository;
import com.cyclone.dineflow.repository.RestaurantTableRepository;
import com.cyclone.dineflow.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final BranchRepository branchRepository;

    @Override
    public RestaurantTableResponseDto createTable(String branchId, RestaurantTableRequestDto restaurantTableRequestDto) {
        Optional <RestaurantTable> foundTableNumber = restaurantTableRepository.findByTableNumber(restaurantTableRequestDto.tableNumber());
        Optional <Branch> foundBranch = branchRepository.findById(branchId);

        if(foundTableNumber.isPresent() && foundBranch.isPresent()){
            throw new RuntimeException("Restaurant table already exists");
        }
        RestaurantTable restaurantTable = RestaurantTable.builder()
                .branch(foundBranch.get())
                .tableNumber(restaurantTableRequestDto.tableNumber())
                .capacity(restaurantTableRequestDto.capacity())
                .location(restaurantTableRequestDto.tableLocation())
                .isActive(true)
                .build();
        restaurantTableRepository.save(restaurantTable);
        return RestaurantTableResponseDtoMapper.toDto(restaurantTable, "Table created successfully");
    }

    @Override
    public List<RestaurantTableResponseDto> getAllTablesOfABranch(String branchId, RestaurantTableRequestDto restaurantTableRequestDto) {
        return List.of();
    }

    @Override
    public List<RestaurantTableResponseDto> getAllAvailableTablesOfABranch(String branchId, RestaurantTableRequestDto restaurantTableRequestDto) {
        List<RestaurantTableResponseDto> restaurantTableList = restaurantTableRepository.findAll().stream().filter((table) -> table.getBranch().getId().equals(branchId)).map((table)-> RestaurantTableResponseDtoMapper.toDto(table,null)).toList();
        return restaurantTableList;
    }

    @Override
    public RestaurantTableResponseDto getParticularTable(String tableId) {
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(tableId).orElseThrow(()-> new RuntimeException("Table not found"));
        return RestaurantTableResponseDtoMapper.toDto(foundRestaurantTable,null);
    }

    @Override
    public String deleteParticularTable(String tableId) {
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(tableId).orElseThrow(()-> new RuntimeException("Table not found"));
        restaurantTableRepository.delete(foundRestaurantTable);
        return "Table deleted successfully";
    }

    @Override
    public String updateParticularTable(String tableId, RestaurantTableRequestDto restaurantTableRequestDto) {
        RestaurantTable foundRestaurantTable = restaurantTableRepository.findById(tableId).orElseThrow(()-> new RuntimeException("Table not found"));

        foundRestaurantTable.setTableNumber(restaurantTableRequestDto.tableNumber());
        foundRestaurantTable.setCapacity(restaurantTableRequestDto.capacity());
        foundRestaurantTable.setLocation(restaurantTableRequestDto.tableLocation());

        restaurantTableRepository.save(foundRestaurantTable);

        return "Table updated successfully";
    }

    @Override
    public String toggleParticularTable(String tableId) {
        return "";
    }
}
