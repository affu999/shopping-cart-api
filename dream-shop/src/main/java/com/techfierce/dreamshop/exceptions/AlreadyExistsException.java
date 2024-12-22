package com.techfierce.dreamshop.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String alreadyExists) {
        super(alreadyExists);
    }
}
