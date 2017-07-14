package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;

import java.util.List;

/**
 * Interface for shop's DAO
 */
public interface DAO {

    /**
     * Returns information about coffee by identifier.
     *
     * @param id identifier of coffee
     * @return information about coffee
     * @throws ProblemWithDatabaseException problem with database
     */
    Coffee getCoffeeById(Integer id) throws ProblemWithDatabaseException;

    /**
     * Returns all grades of coffee.
     *
     * @return list of coffee
     * @throws ProblemWithDatabaseException problem with database
     */
    List<Coffee> getAllCoffee() throws ProblemWithDatabaseException;

    /**
     * Saves order element in database.
     *
     * @param orderElement one order element
     * @throws ProblemWithDatabaseException problem with database
     */
    void setOrderElement(OrderElement orderElement) throws ProblemWithDatabaseException;

    /**
     * Saves order in database.
     *
     * @param order one order
     * @throws ProblemWithDatabaseException problem with database
     */
    void setOrder(Order order) throws ProblemWithDatabaseException;

}
