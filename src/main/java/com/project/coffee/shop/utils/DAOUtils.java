package com.project.coffee.shop.utils;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;

import java.io.IOException;
import java.util.List;

public class DAOUtils {

    private final static CoffeeDAO DAO = new CoffeeDAO();

    public static Coffee getCoffeeById(Integer id) throws IOException {
        try {
            return DAO.getCoffeeById(id);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    public static List<Coffee> getAllCoffee() throws IOException {
        try {
            return DAO.getAllCoffee();
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    public static void setOrderElement(OrderElement orderElement) throws IOException {
        try {
            DAO.setOrderElement(orderElement);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    public static void setOrder(Order order) throws IOException {
        try {
            DAO.setOrder(order);
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

    public static Integer getNextOrderId() throws IOException {
        try {
            return DAO.getNextOrderId();
        } catch (ProblemWithDatabaseException e) {
            throw new IOException("Problems with the database! Please try again later", e);
        }
    }

}
