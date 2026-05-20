package com.cyclone.dineflow.exceptions.custom;

public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException(String restaurantName) {

        super("Restaurant with " + restaurantName + "already exists");
    }
}
