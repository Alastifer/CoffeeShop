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

@WebServlet("/OrderList")
public class OrderListController extends HttpServlet {

    private final static String ATTRIBUTE_ORDER_ELEMENTS = "orderElements";

    private final static String ATTRIBUTE_TOTAL_COST_OF_ORDER_ELEMENTS = "totalCostOfOrderElements";

    private final static String ATTRIBUTE_DELIVERY_COST = "deliveryCost";

    private final static String ATTRIBUTE_COST_OF_ORDER = "costOfOrder";

    private final static String PAGE_OK = "/pages/orderlist.jsp";

    private final static String PART_OF_PARAMETER_AMOUNT = "amountCoffeeGrade";

    private final static String SPLIT_STRING = "_";

    private final static int DELIVERY_COST = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderElement> orderElements = new LinkedList<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        Integer totalCostOfOrderElements = 0;
        Integer costOfOrder;

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();

            if (parameterName.contains(PART_OF_PARAMETER_AMOUNT)) {
                continue;
            }

            Integer id = intValue(parameterName.split(SPLIT_STRING)[1]);
            Coffee coffee = DAOUtils.getCoffeeById(id);

            Integer amount = intValue(req.getParameter(PART_OF_PARAMETER_AMOUNT + SPLIT_STRING + id));
            OrderElement orderElement = new OrderElement(coffee, amount);

            orderElements.add(orderElement);
            totalCostOfOrderElements += orderElement.getTotal();
        }

        costOfOrder = totalCostOfOrderElements + DELIVERY_COST;

        setAttributes(req, orderElements, totalCostOfOrderElements, costOfOrder);

        req.getRequestDispatcher(PAGE_OK).forward(req, resp);
    }

    private Integer intValue(String value) throws IOException {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IOException("No coffee grade with id = " + value, e);
        }
    }

    private void setAttributes(HttpServletRequest req,
                               List<OrderElement> orderElements,
                               Integer totalCostOfOrderElements,
                               Integer costOfOrder) {
        req.setAttribute(ATTRIBUTE_ORDER_ELEMENTS, orderElements);
        req.setAttribute(ATTRIBUTE_TOTAL_COST_OF_ORDER_ELEMENTS, totalCostOfOrderElements);
        req.setAttribute(ATTRIBUTE_DELIVERY_COST, DELIVERY_COST);
        req.setAttribute(ATTRIBUTE_COST_OF_ORDER, costOfOrder);
    }

}
