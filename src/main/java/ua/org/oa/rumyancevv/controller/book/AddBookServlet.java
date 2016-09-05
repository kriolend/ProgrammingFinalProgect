package ua.org.oa.rumyancevv.controller.book;

import ua.org.oa.rumyancevv.model.dto.BookDto;
import ua.org.oa.rumyancevv.service.LibraryService;
import ua.org.oa.rumyancevv.properties.PropertiesInitDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddBookServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = PropertiesInitDB.getService();
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        int balance = Integer.parseInt(req.getParameter("balance"));
        BookDto bookDto = new BookDto(0, author, title, balance);
        long insertedBookId = 0;
        try {
            insertedBookId = service.getBookService().insertBook(bookDto);
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        bookDto.setId(insertedBookId);
        resp.sendRedirect("/admin");
    }
}
