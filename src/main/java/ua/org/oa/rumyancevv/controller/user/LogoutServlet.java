package ua.org.oa.rumyancevv.controller.user;

import ua.org.oa.rumyancevv.model.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto)session.getAttribute("user");
        resp.sendRedirect("index.jsp");
        session.invalidate();
    }
}
