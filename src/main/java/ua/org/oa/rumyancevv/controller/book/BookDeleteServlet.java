package ua.org.oa.rumyancevv.controller.book;

import ua.org.oa.rumyancevv.service.LibraryService;
import ua.org.oa.rumyancevv.properties.PropertiesInitDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = PropertiesInitDB.getService();
        long bookId;
        try {
            bookId = Long.parseLong(req.getParameter("bookId"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/book_catalog");
            return;
        }
        try {
            service.getBookService().deleteBook(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/admin");
    }
}
