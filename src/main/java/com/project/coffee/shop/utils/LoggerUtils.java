package com.project.coffee.shop.utils;

/**
 * Utils for logging.
 */
public class LoggerUtils {

    /**
     * Returns full class name.
     *
     * @return full class name
     */
    public static String getFullClassName() {
        try {
            throw new Exception();
        } catch (Exception e) {
            return String.valueOf(e.getStackTrace()[1].getClassName());
        }
    }

}
