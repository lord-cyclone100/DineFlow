package com.cyclone.dineflow.exceptions.custom;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String menuItemId) {
        super("MenuItem " + menuItemId + " not found");
    }
}
