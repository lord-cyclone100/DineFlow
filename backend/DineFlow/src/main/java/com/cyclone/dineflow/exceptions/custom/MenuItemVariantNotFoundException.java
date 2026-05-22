package com.cyclone.dineflow.exceptions.custom;

public class MenuItemVariantNotFoundException extends RuntimeException {
    public MenuItemVariantNotFoundException(String menuItemVariantId) {

        super("Could not find menu item variant with id: " + menuItemVariantId);
    }
}
