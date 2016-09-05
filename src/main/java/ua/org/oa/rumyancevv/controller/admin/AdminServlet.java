package ua.org.oa.rumyancevv.controller.admin;

import ua.org.oa.rumyancevv.model.dto.ReportDto;
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

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList<BookDto>  books = new LinkedList<>();
        LinkedList<UserDto>  users = new LinkedList<>();
        LinkedList<ReportDto>  reports = new LinkedList<>();
        LibraryService service = PropertiesInitDB.getService();
        try {
            books = service.getBookService().getAllBooks(0);
            users = service.getUserService().getAllUsers();
            reports = service.getBookService().getArchiveReports();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("books", books);
        session.setAttribute("users", users);
        session.setAttribute("reports", reports);
        req.getRequestDispatcher("resources/pages/library/admin.jsp").forward(req, resp);
    }
}
