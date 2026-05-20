package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.RestaurantRequestDto;
import com.cyclone.dineflow.dto.responsedto.RestaurantResponseDto;
import com.cyclone.dineflow.dtomapper.RestaurantResponseDtoMapper;
import com.cyclone.dineflow.entity.Restaurant;
import com.cyclone.dineflow.exceptions.custom.RestaurantAlreadyExistsException;
import com.cyclone.dineflow.exceptions.custom.RestaurantNotFoundException;
import com.cyclone.dineflow.repository.RestaurantRepository;
import com.cyclone.dineflow.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 03-05-2026
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurant) {
        Optional<Restaurant> newRestaurant = restaurantRepository.findByName(restaurant.name());

        if(newRestaurant.isPresent()){
            throw new RestaurantAlreadyExistsException(newRestaurant.get().getName());
        }

        Restaurant addingRestaurant = Restaurant.builder()
                .name(restaurant.name())
                .cuisineType(restaurant.cuisineType())
                .build();

        restaurantRepository.save(addingRestaurant);

        return RestaurantResponseDtoMapper.toDto(addingRestaurant, "Restaurant registered successfully");
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map((restaurant -> RestaurantResponseDtoMapper.toDto(restaurant,null))).toList();
    }

    @Override
    public RestaurantResponseDto getParticularRestaurant(String id) {
        Restaurant foundRestaurant = restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
        return RestaurantResponseDtoMapper.toDto(foundRestaurant,null);
    }

    @Override
    public String updateRestaurant(String id, RestaurantRequestDto restaurant) {
        Restaurant foundRestaurant = restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
        foundRestaurant.setName(restaurant.name());
        foundRestaurant.setCuisineType(restaurant.cuisineType());
        restaurantRepository.save(foundRestaurant);
        return "Restaurant details updated successfully";
    }

    @Override
    public String deleteRestaurant(String id) {
        Restaurant foundRestaurant = restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
        restaurantRepository.delete(foundRestaurant);
        return "Restaurant deleted successfully";
    }
}
