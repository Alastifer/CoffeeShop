package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.project.coffee.shop.utils.LoggerUtils.getFullClassName;

/**
 * Controller for home page of shop.
 */
@WebServlet("/Index")
public class AllCoffeeGradeController extends HttpServlet {

    /**
     * Name of attribute for all coffee.
     */
    private final static String ATTRIBUTE_ALL_COFFEE = "allCoffee";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "/pages/coffeelist.jsp";

    /**
     * URL of error page for problems with database.
     */
    private final static String PAGE_ERROR_WITH_DATABASE = "errorPages/errorInDatabase.jsp";

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(getFullClassName());

    /**
     * Object for access to database.
     */
    private final static DAO dao = new CoffeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coffee> allCoffee;
        try {
            allCoffee = dao.getAllCoffee();
        } catch (ProblemWithDatabaseException e) {
            resp.sendRedirect(PAGE_ERROR_WITH_DATABASE);
            log.error("Problem with database when get all entity", e);
            return;
        }

        req.getSession().setAttribute(ATTRIBUTE_ALL_COFFEE, allCoffee);
        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

}
