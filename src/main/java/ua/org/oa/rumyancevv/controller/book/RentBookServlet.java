package ua.org.oa.rumyancevv.controller.book;

import ua.org.oa.rumyancevv.exceptions.LibraryException;
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

public class RentBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = PropertiesInitDB.getService();
        HttpSession session = req.getSession();
        long bookId;
        bookId = Long.parseLong(req.getParameter("bookId"));
        UserDto user = (UserDto) session.getAttribute("user");
        long userId = user.getId();
        try {
            service.getUserService().userRentsBook(userId, bookId);
        } catch (SQLException e) {
            throw new LibraryException("SQL error");
        }
        resp.sendRedirect("/book_catalog");
    }
}
