package com.project.coffee.shop.controller;

import com.project.coffee.shop.dao.CoffeeDAO;
import com.project.coffee.shop.dao.DAO;
import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.project.coffee.shop.utils.LoggerUtils.getFullClassName;

/**
 * Controller for accept order.
 */
@Controller
public class TakeOrderController {

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
    private final static String PAGE_INCORRECT_ADDRESS = "orderlist";

    /**
     * URL of next page.
     */
    private final static String PAGE_OK = "takeorder";

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
     * @param params all parameters in url
     * @param model model of response
     * @param session HttpSession for get session's attributes
     * @return url
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    @RequestMapping(value = "/takeorder", method = RequestMethod.GET)
    public String takeOrder(@RequestParam Map<String, String> params,
                            ModelMap model,
                            HttpSession session) throws ProblemWithDatabaseException {
        int orderCost = (Integer) session.getAttribute(ATTRIBUTE_COST_OF_ORDER);
        String customerFullName = params.get(PARAMETER_CUSTOMER_FULL_NAME);
        String customerAddress = params.get(PARAMETER_CUSTOMER_ADDRESS);

        if (isEmptyAddress(customerAddress)) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Введите адрес доставки");
            return PAGE_INCORRECT_ADDRESS;
        }

        Order order = new Order(customerFullName, customerAddress, orderCost);
        dao.setOrder(order);

        int orderId = order.getId();
        List<OrderElement> orderElements = (List<OrderElement>) session.getAttribute(ATTRIBUTE_ORDER_ELEMENTS);

        setOrderIdInOrderElements(orderId, orderElements);
        saveOrderElements(orderElements);

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
     * Checks address. Returns true if address is not empty. False if empty.
     * @param address customer's address.
     * @return true/false
     */
    private boolean isEmptyAddress(String address) {
        return address.length() == 0;
    }

    /**
     * Set order id in all order elements.
     *
     * @param orderId order identifier
     * @param orderElements list of order elements
     */
    private void setOrderIdInOrderElements(int orderId, List<OrderElement> orderElements) {
        orderElements.forEach(element -> element.setId(orderId));
    }

    /**
     * Save order elements in database.
     *
     * @param orderElements list of order elements
     * @throws ProblemWithDatabaseException problem with database such as no database created and etc.
     */
    private void saveOrderElements(List<OrderElement> orderElements) throws ProblemWithDatabaseException {
        for (OrderElement element : orderElements) {
            dao.setOrderElement(element);
        }
    }

}
