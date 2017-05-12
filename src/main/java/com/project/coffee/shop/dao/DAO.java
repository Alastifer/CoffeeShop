package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.DAOException;
import com.project.coffee.shop.dao.exception.NoSuchEntityException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;

import java.util.List;

public interface DAO {

    Coffee getCoffeeById(Integer id) throws DAOException, NoSuchEntityException;

    List<Coffee> getAllCoffee() throws DAOException;

    void setOrderElement(OrderElement orderElement) throws DAOException;

    void setOrder(Order order) throws DAOException;

    Integer getNextOrderId() throws DAOException;

}
