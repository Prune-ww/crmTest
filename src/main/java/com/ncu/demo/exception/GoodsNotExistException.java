package com.ncu.demo.exception;

public class GoodsNotExistException extends RuntimeException{
    public GoodsNotExistException() {
    }

    public GoodsNotExistException(String message) {
        super(message);
    }
}
