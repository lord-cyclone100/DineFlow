package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.RestaurantTableRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantTableResponseDto;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 12-05-2026
 */
public interface RestaurantTableService {
    RestaurantTableResponseDto createTable(String branchId, RestaurantTableRequestDto restaurantTableRequestDto);

    List<RestaurantTableResponseDto> getAllTablesOfABranch(String branchId, RestaurantTableRequestDto restaurantTableRequestDto);

    List<RestaurantTableResponseDto> getAllAvailableTablesOfABranch(String branchId, RestaurantTableRequestDto restaurantTableRequestDto);

    RestaurantTableResponseDto getParticularTable(String tableId);

    String deleteParticularTable(String tableId);

    String updateParticularTable(String tableId, RestaurantTableRequestDto restaurantTableRequestDto);

    String toggleParticularTable(String tableId);
}
