package com.cyclone.dineflow.exceptions.custom;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {

      super(message);
    }
}
