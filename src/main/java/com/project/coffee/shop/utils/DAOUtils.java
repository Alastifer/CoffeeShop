package com.project.coffee.shop.utils;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;

import java.io.IOException;
import java.util.List;

/**
 *  Utilitarian component that allows you to access the basic methods of working with the database.
 *  The exceptions thrown by DAO methods turn into IOException and are thrown out by utilitarian methods.
 *
 *  @see CoffeeDAO
 */
public class DAOUtils {

    /**
     * The entity that is responsible for accessing the database.
     */
    private final static CoffeeDAO DAO = new CoffeeDAO();

    /**
     * Returns entity with information about grade of coffee.
     *
     * @param id identifier of grade
     * @return entity that represents coffee
     * @throws IOException if exception is thrown from the DAO
     */
    public static Coffee getCoffeeById(Integer id) throws IOException {
        try {
            return DAO.getCoffeeById(id);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    /**
     * Returns list of all grades of coffee.
     *
     * @return list of grades
     * @throws IOException if exception is thrown from the DAO
     */
    public static List<Coffee> getAllCoffee() throws IOException {
        try {
            return DAO.getAllCoffee();
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    /**
     * Saves information about the ordered coffee grade to the database.
     *
     * @param orderElement entity that represent the ordered coffee grade
     * @throws IOException if exception is thrown from the DAO
     */
    public static void setOrderElement(OrderElement orderElement) throws IOException {
        try {
            DAO.setOrderElement(orderElement);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    /**
     * Saves order information to the database.
     *
     * @param order entity that represent one order
     * @throws IOException if exception is thrown from the DAO
     */
    public static void setOrder(Order order) throws IOException {
        try {
            DAO.setOrder(order);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    /**
     * Returns the following identifier that used to save the order in the database.
     *
     * @return next identifier for order
     * @throws IOException if exception is thrown from the DAO
     */
    public static Integer getNextOrderId() throws IOException {
        try {
            return DAO.getNextOrderId();
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

}
