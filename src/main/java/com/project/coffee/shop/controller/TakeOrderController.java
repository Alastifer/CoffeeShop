package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;
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
 * Controller for accept order.
 */
@WebServlet("/TakeOrder")
public class TakeOrderController extends HttpServlet {

    /**
     * Name of attribute for price of order.
     */
    private final static String ATTRIBUTE_COST_OF_ORDER = "orderCost";

    /**
     * Name of attribute for error message.
     */
    private final static String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

    /**
     * Name of attribute for all order elements.
     */
    private final static String ATTRIBUTE_ORDER_ELEMENTS = "orderElements";

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
        Integer orderCost = (Integer) req.getSession().getAttribute(ATTRIBUTE_COST_OF_ORDER);
        String customerFullName = req.getParameter(PARAMETER_CUSTOMER_FULL_NAME);
        String customerAddress = req.getParameter(PARAMETER_CUSTOMER_ADDRESS);

        if (isEmptyAddress(customerAddress)) {
            redirectToCurrentPageWithErrorMessage(req, resp, "Введите адрес доставки");
            return;
        }

        Order order = new Order(customerFullName, customerAddress, orderCost);

        try {
            dao.setOrder(order);
        }  catch (ProblemWithDatabaseException e) {
            resp.sendRedirect(PAGE_ERROR_WITH_DATABASE);
            log.error("Problem with database when set in database", e);
            return;
        }

        Integer orderId = order.getId();
        List<OrderElement> orderElements = (List<OrderElement>) req.getSession().getAttribute(ATTRIBUTE_ORDER_ELEMENTS);

        for (OrderElement orderElement : orderElements) {
            orderElement.setOrderId(orderId);

            try {
                dao.setOrderElement(orderElement);
            } catch (ProblemWithDatabaseException e) {
                resp.sendRedirect(PAGE_ERROR_WITH_DATABASE);
                log.error("Problem with database when set in database", e);
                return;
            }
        }

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
    private void redirectToCurrentPageWithErrorMessage(HttpServletRequest req,
                                                       HttpServletResponse resp,
                                                       String errorMessage) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, errorMessage);
        req.getRequestDispatcher(PAGE_INCORRECT_ADDRESS).forward(req, resp);
    }

}
