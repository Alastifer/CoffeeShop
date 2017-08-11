package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.OrderElement;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.project.coffee.shop.utils.LoggerUtils.getFullClassName;

/**
 * Controller for entering contact information and order viewing.
 */
@Controller
public class OrderListController {

    /**
     * Name of attribute for all order elements.
     */
    private final static String ATTRIBUTE_ORDER_ELEMENTS = "orderElements";

    /**
     * Name of attribute for price of order.
     */
    private final static String ATTRIBUTE_COST_OF_ORDER = "orderCost";

    /**
     * Name of attribute for error message.
     */
    private final static String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "orderlist";

    /**
     * URL of error page for problems with database.
     */
    private final static String PAGE_ERROR_IN_DATABASE = "errorInDatabase";

    /**
     * URL of page if entered incorrect values.
     */
    private final static String PAGE_INCORRECT_VALUE = "coffeelist";

    /**
     * Part of parameter name amountCoffeeGrade_[number of coffee].
     */
    private final static String PART_OF_PARAMETER_AMOUNT = "amountCoffeeGrade";

    /**
     * Delimiter between part of parameter name amountCoffeeGrade_[number of coffee].
     */
    private final static String SPLIT_STRING = "_";

    /**
     * Delivery cost.
     */
    private final static int DELIVERY_COST = 5;

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
     * @param params all parameters in url
     * @param model model of response
     * @param session HttpSession for set session's attributes
     * @return url
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    @RequestMapping(value = "/orderlist", method = RequestMethod.GET)
    public String viewOrderList(@RequestParam Map<String, String> params,
                                ModelMap model,
                                HttpSession session) throws ProblemWithDatabaseException {
        List<OrderElement> orderElements = new LinkedList<>();
        Integer orderElementsCost = 0;
        Integer orderCost;

        for (String parameterName : params.keySet()) {
            if (parameterName.contains(PART_OF_PARAMETER_AMOUNT)) {
                continue;
            }

            Integer id = Integer.parseInt(parameterName.split(SPLIT_STRING)[1]);

            Coffee coffee = dao.getCoffeeById(id);

            Integer coffeeCups = getIntegerValue(params.get(PART_OF_PARAMETER_AMOUNT + SPLIT_STRING + id));
            if (coffeeCups == null || coffeeCups <= 0) {
                model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Введено некорректное значение");
                return PAGE_INCORRECT_VALUE;
            }

            OrderElement orderElement = new OrderElement(coffee, coffeeCups);
            orderElements.add(orderElement);

            orderElementsCost += orderElement.getTotalCost();
        }

        if (orderElementsCost == 0) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Не выбрано ни одного сорта кофе");
            return PAGE_INCORRECT_VALUE;
        }

        orderCost = orderElementsCost + DELIVERY_COST;

        setOrderElements(orderElements);
        setAttributesInSession(session, orderElements, orderCost);

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

    /**
     * Returns integer value of string.
     *
     * @param s number in string
     * @return number or null if throw NumberFormatException
     */
    private Integer getIntegerValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Saves all order elements in database.
     *
     * @param orderElements list of order elements
     * @throws ProblemWithDatabaseException if an exception was thrown out from DAO
     */
    private void setOrderElements(List<OrderElement> orderElements) throws ProblemWithDatabaseException {
        for (OrderElement orderElement : orderElements) {
            dao.setOrderElement(orderElement);
        }
    }

    /**
     * Sets attributes in session.
     *
     * @param session model of order list
     * @param orderElements list of all order elements
     * @param orderCost price of order
     */
    private void setAttributesInSession(HttpSession session,
                                        List<OrderElement> orderElements,
                                        Integer orderCost) {
        session.setAttribute(ATTRIBUTE_ORDER_ELEMENTS, orderElements);
        session.setAttribute(ATTRIBUTE_COST_OF_ORDER, orderCost);
    }

}
