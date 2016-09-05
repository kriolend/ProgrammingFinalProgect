package ua.org.oa.rumyancevv.service;

import ua.org.oa.rumyancevv.dao.BookDao;
import ua.org.oa.rumyancevv.dao.ReportDao;
import ua.org.oa.rumyancevv.dao.UserDao;
import ua.org.oa.rumyancevv.service.impl.BookServiceImpl;
import ua.org.oa.rumyancevv.service.impl.DatabaseServiceImpl;
import ua.org.oa.rumyancevv.service.impl.UserServiceImpl;

public class LibraryService {

    private static LibraryService instance = null;

    private UserService userService;
    private BookService bookService;
    private DatabaseService databaseService;

    private LibraryService(UserDao userDao, BookDao bookDao, ReportDao reportDao, ReportDao rentsDao) {
        userService = new UserServiceImpl(userDao, bookDao, reportDao, rentsDao);
        bookService = new BookServiceImpl(userDao, bookDao, reportDao, rentsDao);
        databaseService = new DatabaseServiceImpl();
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public static LibraryService getInstance(UserDao userDao, BookDao bookDao, ReportDao reportDao, ReportDao rentsDao) {
        if (instance == null) {
            instance = new LibraryService(userDao, bookDao, reportDao, rentsDao);
        }
        return instance;
    }
}