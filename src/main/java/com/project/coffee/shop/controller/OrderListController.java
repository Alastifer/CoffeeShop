package com.project.coffee.shop.controller;

import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.OrderElement;
import com.project.coffee.shop.utils.DAOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for entering contact information and order viewing.
 */
@WebServlet("/OrderList")
public class OrderListController extends HttpServlet {

    /**
     * Name of attribute for all order elements.
     */
    private final static String ATTRIBUTE_ORDER_ELEMENTS = "orderElements";

    /**
     * Name of attribute for price of all order elements.
     */
    private final static String ATTRIBUTE_TOTAL_COST_OF_ORDER_ELEMENTS = "totalCostOfOrderElements";

    /**
     * Name of attribute for price of delivery.
     */
    private final static String ATTRIBUTE_DELIVERY_COST = "deliveryCost";

    /**
     * Name of attribute for price of order.
     */
    private final static String ATTRIBUTE_COST_OF_ORDER = "costOfOrder";

    /**
     * Name of attribute for order identifier.
     */
    private final static String ATTRIBUTE_ORDER_ID = "orderId";

    /**
     * Name of attribute for error message.
     */
    private final static String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "/pages/orderlist.jsp";

    /**
     * URL of page if entered incorrect values.
     */
    private final static String PAGE_INCORRECT_VALUE = "/pages/coffeelist.jsp";

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderElement> orderElements = new LinkedList<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        Integer totalCostOfOrderElements = 0;
        Integer costOfOrder;
        Integer orderId = DAOUtils.getNextOrderId();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.contains(PART_OF_PARAMETER_AMOUNT)) {
                continue;
            }

            Integer id = intValue(parameterName.split(SPLIT_STRING)[1]);
            Coffee coffee = DAOUtils.getCoffeeById(id);

            Integer amount = intValue(req.getParameter(PART_OF_PARAMETER_AMOUNT + SPLIT_STRING + id));
            if (isNull(amount)) {
                redirectToErrorPage(req, resp, "Введено неверное значение");
                return;
            }

            OrderElement orderElement = new OrderElement(orderId, coffee, amount);
            orderElements.add(orderElement);

            totalCostOfOrderElements += orderElement.getTotalCost();
        }

        if (totalCostOfOrderElements == 0) {
            redirectToErrorPage(req, resp, "Не выбрано ни одного сорта кофе");
            return;
        }

        setOrderElements(orderElements);
        costOfOrder = totalCostOfOrderElements + DELIVERY_COST;
        setAttributes(req, orderElements, totalCostOfOrderElements, costOfOrder, orderId);
        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

    /**
     * Saves all order elements in database.
     *
     * @param orderElements list of order elements
     * @throws IOException if an exception was thrown out of DAO
     */
    private void setOrderElements(List<OrderElement> orderElements) throws IOException {
        for (OrderElement orderElement : orderElements) {
            DAOUtils.setOrderElement(orderElement);
        }
    }

    /**
     * Redirect to error page.
     *
     * @param req request
     * @param resp response
     * @param errorMessage error message
     * @throws ServletException exception of HttpServlet
     * @throws IOException exception of HttpServlet
     */
    private void redirectToErrorPage(HttpServletRequest req,
                                     HttpServletResponse resp,
                                     String errorMessage) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, errorMessage);
        req.getRequestDispatcher(PAGE_INCORRECT_VALUE).forward(req, resp);
    }

    /**
     * Returns integer if string is valid integer value.
     * Else returns null.
     *
     * @param value string with numbers
     * @return integer value or null
     * @throws IOException if was thrown NumberFormatException
     */
    private Integer intValue(String value) throws IOException {
        try {
            return checkValue(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Checks integer value. Returns value if valid and null if invalid value.
     *
     * @param value number
     * @return value or null
     */
    private Integer checkValue(Integer value) {
        return value < 1 ? null : value;
    }

    /**
     * Zero check.
     *
     * @param value integer value
     * @return true/false (null/not null)
     */
    private boolean isNull(Integer value) {
        return value == null;
    }

    /**
     * Sets attributes in session.
     *
     * @param req request for set attributes
     * @param orderElements list of all order elements
     * @param totalCostOfOrderElements price of all order elements
     * @param costOfOrder price of order
     * @param orderId order identifier
     */
    private void setAttributes(HttpServletRequest req,
                               List<OrderElement> orderElements,
                               Integer totalCostOfOrderElements,
                               Integer costOfOrder,
                               Integer orderId) {
        req.getSession().setAttribute(ATTRIBUTE_ORDER_ELEMENTS, orderElements);
        req.getSession().setAttribute(ATTRIBUTE_TOTAL_COST_OF_ORDER_ELEMENTS, totalCostOfOrderElements);
        req.getSession().setAttribute(ATTRIBUTE_DELIVERY_COST, DELIVERY_COST);
        req.getSession().setAttribute(ATTRIBUTE_COST_OF_ORDER, costOfOrder);
        req.getSession().setAttribute(ATTRIBUTE_ORDER_ID, orderId);
    }

}
