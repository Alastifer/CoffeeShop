package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.DAOException;
import com.project.coffee.shop.dao.exception.NoSuchEntityException;
import com.project.coffee.shop.entity.Coffee;

import java.util.List;

public interface DAO {

    Coffee getCoffeeById(Integer id) throws NoSuchEntityException, DAOException;

    List<Coffee> getAllCoffee() throws DAOException;

}
