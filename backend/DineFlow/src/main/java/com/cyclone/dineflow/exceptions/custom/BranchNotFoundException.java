package com.cyclone.dineflow.exceptions.custom;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(String branchId) {
        super("Branch with id " + branchId + " not found");
    }
}
