package ua.org.oa.rumyancevv.controller.base;

import ua.org.oa.rumyancevv.model.dto.UserDto;
import ua.org.oa.rumyancevv.service.LibraryService;
import ua.org.oa.rumyancevv.properties.PropertiesInitDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = PropertiesInitDB.getService();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        UserDto userDto = new UserDto(0, name, login, password, birthday);
        long insertedUserId = 0;
        try {
            insertedUserId = service.getUserService().insertUser(userDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userDto.setId(insertedUserId);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto);
        resp.sendRedirect("/book_catalog");
    }
}
