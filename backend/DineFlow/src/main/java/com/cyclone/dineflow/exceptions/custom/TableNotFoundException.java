package com.cyclone.dineflow.exceptions.custom;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(String tableId) {

        super("Table " + tableId + " not found");
    }
}
