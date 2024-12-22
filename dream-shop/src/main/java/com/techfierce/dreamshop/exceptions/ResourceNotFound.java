package com.techfierce.dreamshop.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String productNotFound) {
        super(productNotFound);
    }
}
