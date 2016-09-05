package ua.org.oa.rumyancevv.controller.exeptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError (req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        HttpSession session = req.getSession();
        String exceptionClassName = throwable.getClass().getName();
        String errorMessage = null;
        switch (exceptionClassName){
            case "java.lang.NumberFormatException": {
                errorMessage = "wrong number";
                break;
            }
            default: {
                if (throwable.getMessage() != null){
                    errorMessage = throwable.getMessage();
                }else {
                    errorMessage = exceptionClassName;
                }
            }
        }

        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        String redirectUrl = null;
        switch (requestUri){
            case "/rent":{
                redirectUrl = "/book_catalog";
                break;
            }
            case "/return":{
                redirectUrl = "/book_catalog";
                break;
            }
            default: redirectUrl = "/";
        }
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("redirectUrl", redirectUrl);
        req.getRequestDispatcher("/resources/pages/error_page.jsp").forward(req, resp);
    }
}
