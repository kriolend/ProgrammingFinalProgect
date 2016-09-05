package ua.org.oa.rumyancevv.properties;

import ua.org.oa.rumyancevv.dao.*;
import ua.org.oa.rumyancevv.dao.DatabaseDao;
import ua.org.oa.rumyancevv.exceptions.StorageException;
import ua.org.oa.rumyancevv.service.LibraryService;

public class PropertiesInitDB {
    private static LibraryService service = null;

    private PropertiesInitDB() {
    }

    public static synchronized LibraryService getService() throws StorageException {
        if (service == null) {
            DAO DAO = new DatabaseDao();
            UserDao userDao = DAO.getUserDao();
            BookDao bookDao = DAO.getBookDao();
            ReportDao reportDao = DAO.getReportDao();
            ReportDao rentsDao = DAO.getRentsDao();
            service = LibraryService.getInstance(userDao, bookDao, reportDao, rentsDao);
        }
        return service;
    }
}
