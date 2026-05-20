package com.cyclone.dineflow.exceptions.custom;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryId) {

        super("Category with id " + categoryId + " not found");
    }
}
