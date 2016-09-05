package ua.org.oa.rumyancevv.controller.user;

import ua.org.oa.rumyancevv.controller.base.SqlImportServlet;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.model.dto.UserDto;
import ua.org.oa.rumyancevv.service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import static ua.org.oa.rumyancevv.properties.PropertiesUtils.getProperties;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String psw = req.getParameter("password");
        String adminLogin = null;
        String adminPassword = null;
        try {
            adminLogin = getProperties().getProperty("admin_login");
            adminPassword = getProperties().getProperty("admin_password");
        } catch (PropertiesException e) {
            e.printStackTrace();
        }
        if (login.equals(adminLogin) && psw.equals(adminPassword)) {
            UserDto userDto = new UserDto("admin", adminLogin, adminPassword);
            req.getSession().setAttribute("user", userDto);
            resp.sendRedirect("/admin");
        } else {
            LibraryService service = SqlImportServlet.service;
            try {
                LinkedList<UserDto> users = service.getUserService().getAllUsers();
                for (UserDto userDto : users) {
                    if (login.equals(userDto.getLogin()) && psw.equals(userDto.getPassword())) {
                        HttpSession session = req.getSession();
                        session.setAttribute("user", userDto);
                        resp.sendRedirect("/book_catalog");
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/resources/pages/notAuth.jsp").forward(req, resp);
        }

    }
}
