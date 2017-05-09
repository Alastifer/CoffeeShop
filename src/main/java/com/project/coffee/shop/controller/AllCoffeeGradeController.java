package com.project.coffee.shop.controller;

import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.utils.DAOUtils;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coffee> allCoffee = DAOUtils.getAllCoffee();
        req.setAttribute(ATTRIBUTE_ALL_COFFEE, allCoffee);
        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

}
