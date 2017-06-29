package com.project.coffee.shop.dao.exception;

public class ProblemWithDatabaseException extends DAOException {

    public ProblemWithDatabaseException(String message) {
        super(message);
    }

    public ProblemWithDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
