package com.ncu.demo.exception;

public class InventoryNotEnoughException extends RuntimeException{
    public InventoryNotEnoughException() {
    }

    public InventoryNotEnoughException(String message) {
        super(message);
    }
}
