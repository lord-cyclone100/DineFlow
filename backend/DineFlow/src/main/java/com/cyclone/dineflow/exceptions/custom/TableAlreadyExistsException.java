package com.cyclone.dineflow.exceptions.custom;

public class TableAlreadyExistsException extends RuntimeException {
    public TableAlreadyExistsException(String tableNumber, String branchName) {
        super("Table already exists for branch " + branchName + " and table number " + tableNumber);
    }
}
