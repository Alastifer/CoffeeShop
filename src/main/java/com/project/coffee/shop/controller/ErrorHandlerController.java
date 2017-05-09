package com.project.coffee.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ErrorHandler")
public class ErrorHandlerController extends HttpServlet {

    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

    private static final String ATTRIBUTE_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    private static final String ATTRIBUTE_SERVLET_ERROR_MESSAGE = "javax.servlet.error.message";

    private static final String PAGE_ERROR = "errorPages/error.jsp";

    private static final String ERROR_MESSAGE_PAGE_NOT_FOUND = "404 : page not found";

    private static final Integer CODE_ERROR_PAGE_NOT_FOUND = 404;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer code = (Integer) req.getAttribute(ATTRIBUTE_SERVLET_ERROR_STATUS_CODE);
        String errorMessage = (String) req.getAttribute(ATTRIBUTE_SERVLET_ERROR_MESSAGE);

        if (code.equals(CODE_ERROR_PAGE_NOT_FOUND)) {
            req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, ERROR_MESSAGE_PAGE_NOT_FOUND);
        } else {
            req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, errorMessage);
        }

        req.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
    }

}
