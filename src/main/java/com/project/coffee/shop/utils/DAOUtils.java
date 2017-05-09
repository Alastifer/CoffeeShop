package com.project.coffee.shop.utils;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.exception.DAOException;
import com.project.coffee.shop.dao.exception.NoSuchEntityException;
import com.project.coffee.shop.entity.Coffee;

import java.io.IOException;
import java.util.List;

public class DAOUtils {

    private final static CoffeeDAO DAO = new CoffeeDAO();

    public static Coffee getCoffeeById(Integer id) throws IOException {
        try {
            return DAO.getCoffeeById(id);
        } catch (DAOException | NoSuchEntityException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static List<Coffee> getAllCoffee() throws IOException {
        try {
            return DAO.getAllCoffee();
        } catch (DAOException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

}
