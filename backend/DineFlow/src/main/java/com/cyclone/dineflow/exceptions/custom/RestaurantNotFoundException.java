package com.cyclone.dineflow.exceptions.custom;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String id) {

        super("Restaurant with " + id + " not found");
    }
}
