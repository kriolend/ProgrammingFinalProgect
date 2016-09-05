package ua.org.oa.rumyancevv.controller.base;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.exceptions.ServiceException;
import ua.org.oa.rumyancevv.exceptions.StorageException;
import ua.org.oa.rumyancevv.model.dto.BookDto;
import ua.org.oa.rumyancevv.service.LibraryService;
import ua.org.oa.rumyancevv.properties.PropertiesInitDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.SQLException;

import static ua.org.oa.rumyancevv.properties.PropertiesUtils.getProperties;
import static ua.org.oa.rumyancevv.properties.PropertiesUtils.readProperties;

public class SqlImportServlet implements ServletContextListener {

    public static LibraryService service;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String propFilePath = null;
        String jdbcDriver = null;
        String storageType = null;
        ServletContext context = sce.getServletContext();
        try {
            propFilePath = context.getRealPath("properties\\library.properties");
            readProperties(propFilePath);
            jdbcDriver = getProperties().getLibraryProperty("JDBC_DRIVER_CLASS");
            storageType = getProperties().getLibraryProperty("storage_type");
        } catch (PropertiesException e) {
            context.setAttribute("contextInitError", e.getMessage());
            return;
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            context.setAttribute("contextInitError", "JDBC Driver load error");
            return;
        }

        try {
            service = PropertiesInitDB.getService();
        } catch (StorageException e) {
            context.setAttribute("contextInitError", "Storage error");
            return;
        }

        String dbInitSqlPath = context.getRealPath("resources\\SQL\\library_init.sql");
        try {
            if (storageType.equals("MYSQL")) {
                service.getDatabaseService().dataBaseInit(dbInitSqlPath);
                BookDto bookDto = new BookDto(1, "Брюсь Эккель", "«Философия Java»", 5);
                service.getBookService().insertBook(bookDto);
                bookDto = new BookDto(1, "Герберт Шилдт", "Java. Руководство для начинающих", 6);
                service.getBookService().insertBook(bookDto);
                bookDto = new BookDto(1, "Кея Хорстманна", "Java 2", 7);
                service.getBookService().insertBook(bookDto);
                bookDto = new BookDto(1, "Кен Арнолд, Джеймс Гослинг, Дэвид Холмс", "Язык программирования Java", 4);
                service.getBookService().insertBook(bookDto);
                bookDto = new BookDto(1, "Джошуа Блох", "Java. Эффективное программирование", 4);
                service.getBookService().insertBook(bookDto);
            }
        } catch (PropertiesException e) {
            context.setAttribute("contextInitError", e.getMessage());
            return;
        } catch (ConnectionProviderException e) {
            context.setAttribute("contextInitError", "Connection provider error");
            return;
        } catch (SQLException e) {
            context.setAttribute("contextInitError", "SQL error");
            return;
        } catch (ServiceException e) {
        } catch (IOException e) {
            context.setAttribute("contextInitError", "DB init error");
            return;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
