package com.project.coffee.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for errors and exceptions.
 */
@WebServlet("/ErrorHandler")
public class ErrorHandlerController extends HttpServlet {

    /**
     * Name of attribute for error message.
     */
    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";

    /**
     * Name of attribute for code of error.
     */
    private static final String ATTRIBUTE_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    /**
     * Name of attribute for error message from servlet.
     */
    private static final String ATTRIBUTE_SERVLET_ERROR_MESSAGE = "javax.servlet.error.message";

    /**
     * URL of error page.
     */
    private static final String PAGE_ERROR = "/errorPages/error.jsp";

    /**
     * Message for error 404.
     */
    private static final String ERROR_MESSAGE_PAGE_NOT_FOUND = "404 : page not found";

    /**
     * Code of error "Page not found".
     */
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
