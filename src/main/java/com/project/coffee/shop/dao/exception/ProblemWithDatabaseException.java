package com.project.coffee.shop.dao.exception;

/**
 * Exception for problem with database such as no database created and etc.
 */
public class ProblemWithDatabaseException extends DAOException {

    /**
     * Constructs exception with message.
     *
     * @param message exception's message
     */
    public ProblemWithDatabaseException(String message) {
        super(message);
    }

    /**
     * Constructs exception with message and cause.
     *
     * @param message exception's message
     * @param cause cause of exception
     */
    public ProblemWithDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
