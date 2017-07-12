package com.project.coffee.shop.controller;

import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.utils.DAOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for accept order.
 */
@WebServlet("/TakeOrder")
public class TakeOrderController extends HttpServlet {

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
     * Name of parameter for customer's full name.
     */
    private final static String PARAMETER_CUSTOMER_FULL_NAME = "customerFullName";

    /**
     * Name of parameter for customer's address.
     */
    private final static String PARAMETER_CUSTOMER_ADDRESS = "customerAddress";

    /**
     * URL of page for incorrect address.
     */
    private final static String PAGE_INCORRECT_ADDRESS = "/pages/orderlist.jsp";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "/pages/orderOK.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer order_id = (Integer) req.getSession().getAttribute(ATTRIBUTE_ORDER_ID);
        Integer costOfOrder = (Integer) req.getSession().getAttribute(ATTRIBUTE_COST_OF_ORDER);
        String customerFullName = req.getParameter(PARAMETER_CUSTOMER_FULL_NAME);
        String customerAddress = req.getParameter(PARAMETER_CUSTOMER_ADDRESS);

        if (isEmptyAddress(customerAddress)) {
            redirectToErrorPage(req, resp, "Введите адрес доставки");
            return;
        }

        Order order = new Order(order_id, customerFullName, customerAddress, costOfOrder);
        DAOUtils.setOrder(order);

        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

    /**
     * Checks address. Returns true if address is not empty. False if empty.
     * @param address customer's address.
     * @return true/false
     */
    private boolean isEmptyAddress(String address) {
        return address.length() == 0;
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
        req.getRequestDispatcher(PAGE_INCORRECT_ADDRESS).forward(req, resp);
    }

}
