package com.cyclone.dineflow.exceptions.custom;

public class MenuItemVariantAlreadyExistsException extends RuntimeException {
    public MenuItemVariantAlreadyExistsException(String menuItemVariantName, String menuItemName) {

        super("Variant with name " + menuItemVariantName + " for Menu item " + menuItemName + " already exists!");
    }
}
