package ua.org.oa.rumyancevv.controller.book;

import ua.org.oa.rumyancevv.model.dto.BookDto;
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
import java.util.LinkedList;

public class BookCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = PropertiesInitDB.getService();
        LinkedList<BookDto> allBooks = null;
        LinkedList<BookDto> rentBooks = null;
        try {
            HttpSession session = req.getSession();
            UserDto user = (UserDto)session.getAttribute("user");
            allBooks = service.getBookService().getAllBooks(0);
            rentBooks = service.getBookService().booksRentedByUser(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("allBooks", allBooks);
        session.setAttribute("rentBooks", rentBooks);
        req.getRequestDispatcher("resources/pages/library/userMainPage.jsp").forward(req, resp);
    }
}
