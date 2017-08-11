package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.coffee.shop.utils.LoggerUtils.getFullClassName;

/**
 * Controller for home page of shop.
 */
@Controller
public class AllCoffeeGradeController {

    /**
     * Name of attribute for all coffee.
     */
    private final static String ATTRIBUTE_ALL_COFFEE = "allCoffee";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "coffeelist";

    /**
     * URL of error page for problems with database.
     */
    private final static String PAGE_ERROR_IN_DATABASE = "errorInDatabase";

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(getFullClassName());

    /**
     * Object for access to database.
     */
    private final static DAO dao = new CoffeeDAO();

    /**
     * Get request.
     *
     * @param model of order list
     * @return url
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewAllCoffee(ModelMap model) throws ProblemWithDatabaseException {
        List<Coffee> allCoffee = dao.getAllCoffee();
        model.addAttribute(ATTRIBUTE_ALL_COFFEE, allCoffee);
        return PAGE_OK;
    }

    /**
     * Handle ProblemWithDatabaseException.
     *
     * @param e exception
     * @return url
     */
    @ExceptionHandler(ProblemWithDatabaseException.class)
    public String handlerDAOError(Exception e) {
        log.error("Problem with database", e);
        return PAGE_ERROR_IN_DATABASE;
    }

}
