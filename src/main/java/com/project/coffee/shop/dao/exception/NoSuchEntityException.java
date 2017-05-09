package com.project.coffee.shop.dao.exception;

public class NoSuchEntityException extends DAOException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
