package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.OrderElement;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final static String PAGE_INCORRECT_VALUE = "";

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
    @RequestMapping(value = "/orderlist", method = RequestMethod.POST)
    public String viewOrderList(@RequestParam Map<String, String> params,
                                ModelMap model,
                                HttpSession session) throws ProblemWithDatabaseException {
        Set<String> orderElementsTitle = getOrderElementsTitle(params);
        List<OrderElement> orderElements = getOrderElements(orderElementsTitle, params);
        int orderElementsCost = calculateOrderElementsCost(orderElements);

        if (isCostZero(orderElementsCost)) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Не выбрано ни одного сорта кофе");
            return "forward:/" + PAGE_INCORRECT_VALUE;
        }

        saveOrderElements(orderElements);
        int orderCost = orderElementsCost + DELIVERY_COST;
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
     * Handle NumberFormatException.
     *
     * @param e exception
     * @return model and view
     */
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handlerNumberFormat(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ATTRIBUTE_ERROR_MESSAGE, "Введено некорректное значение");
        modelAndView.setViewName("forward:/" + PAGE_INCORRECT_VALUE);
        return modelAndView;
    }

    /**
     * Check cost by zero.
     *
     * @param orderElementsCost total cost of all order elements
     * @return true if cost is zero
     */
    private boolean isCostZero(int orderElementsCost) {
        return orderElementsCost == 0;
    }

    /**
     * Returns list of all order elements.
     *
     * @param orderElementsTitle set of order elements title
     * @param params parameters in url
     * @return list of order elements
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    private List<OrderElement> getOrderElements(Set<String> orderElementsTitle, Map<String, String> params) throws ProblemWithDatabaseException {
        List<OrderElement> orderElements = new ArrayList<>();

        for (String element : orderElementsTitle) {
            int id = Integer.parseInt(element.split(SPLIT_STRING)[1]);
            Coffee coffee = dao.getCoffeeById(id);
            int coffeeCups = Integer.parseInt(params.get(PART_OF_PARAMETER_AMOUNT + SPLIT_STRING + id));
            checkNumberOfCups(coffeeCups);
            orderElements.add(new OrderElement(coffee, coffeeCups));
        }

        return orderElements;
    }

    /**
     * Returns total cost of all order elements.
     *
     * @param orderElements list of order elements
     * @return total cost of order elements
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    private int calculateOrderElementsCost(List<OrderElement> orderElements) {
        return orderElements.stream()
                .mapToInt(OrderElement::getTotalCost)
                .reduce(0, (left, right) -> left + right);
    }

    /**
     * Check number of coffee's cups.
     *
     * @param coffeeCups number of cups
     * @throws NumberFormatException throw if cups lower or equals 0
     */
    private void checkNumberOfCups(int coffeeCups) throws NumberFormatException {
        if (coffeeCups <= 0) {
            throw new NumberFormatException();
        }
    }

    /**
     * Returns set of order elements title.
     *
     * @param params parameters in url
     * @return set of order elements title
     */
    private Set<String> getOrderElementsTitle(Map<String, String> params) {
        return params.keySet().stream()
                .filter(s -> !s.contains(PART_OF_PARAMETER_AMOUNT))
                .collect(Collectors.toSet());
    }

    /**
     * Saves all order elements in database.
     *
     * @param orderElements list of order elements
     * @throws ProblemWithDatabaseException if an exception was thrown out from DAO
     */
    private void saveOrderElements(List<OrderElement> orderElements) throws ProblemWithDatabaseException {
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
                                        int orderCost) {
        session.setAttribute(ATTRIBUTE_ORDER_ELEMENTS, orderElements);
        session.setAttribute(ATTRIBUTE_COST_OF_ORDER, orderCost);
    }

}
