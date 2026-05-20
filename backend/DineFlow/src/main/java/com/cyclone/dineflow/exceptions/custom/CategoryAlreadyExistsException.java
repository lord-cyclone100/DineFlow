package com.cyclone.dineflow.exceptions.custom;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String categoryName, String branchName) {

        super("Branch with name " + categoryName + " already exists: " + branchName);
    }
}
