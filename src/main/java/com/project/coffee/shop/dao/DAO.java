package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;

import java.util.List;

public interface DAO {

    Coffee getCoffeeById(Integer id) throws ProblemWithDatabaseException;

    List<Coffee> getAllCoffee() throws ProblemWithDatabaseException;

    void setOrderElement(OrderElement orderElement) throws ProblemWithDatabaseException;

    void setOrder(Order order) throws ProblemWithDatabaseException;

    Integer getNextOrderId() throws ProblemWithDatabaseException;

}
