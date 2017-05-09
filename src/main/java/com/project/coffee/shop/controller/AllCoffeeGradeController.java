package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.exception.DAOException;
import com.project.coffee.shop.entity.Coffee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Index")
public class AllCoffeeGradeController extends HttpServlet {

    private final static String ATTRIBUTE_ALL_COFFEE = "allCoffee";

    private final static String PAGE_OK = "/pages/coffeelist.jsp";

    private final CoffeeDAO coffeeDAO = new CoffeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coffee> allCoffee = getAllCoffee();
        req.setAttribute(ATTRIBUTE_ALL_COFFEE, allCoffee);
        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

    private List<Coffee> getAllCoffee() throws IOException {
        try {
            return coffeeDAO.getAllCoffee();
        } catch (DAOException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

}
