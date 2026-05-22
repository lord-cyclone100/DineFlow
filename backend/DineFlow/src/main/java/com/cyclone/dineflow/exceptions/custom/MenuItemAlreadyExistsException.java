package com.cyclone.dineflow.exceptions.custom;

public class MenuItemAlreadyExistsException extends RuntimeException {
    public MenuItemAlreadyExistsException(String menuItemName, String categoryName, String branchName) {
        super("MenuItem " + menuItemName + " for Category " + categoryName + " already exists for Branch " + branchName);
    }
}
