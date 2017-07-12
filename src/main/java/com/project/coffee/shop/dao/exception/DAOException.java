package com.project.coffee.shop.dao.exception;

/**
 * Main exception for DAO.
 */
public class DAOException extends Exception {

    /**
     * Constructs exception with message.
     *
     * @param message exception's message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs exception with message and cause.
     *
     * @param message exception's message
     * @param cause cause of exception
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
