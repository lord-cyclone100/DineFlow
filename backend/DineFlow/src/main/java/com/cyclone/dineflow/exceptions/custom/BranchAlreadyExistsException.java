package com.cyclone.dineflow.exceptions.custom;

public class BranchAlreadyExistsException extends RuntimeException {
    public BranchAlreadyExistsException(String branchName, String restaurantName) {
        super("Branch with name " + branchName + " for restaurant " + restaurantName + "already exists");
    }
}
