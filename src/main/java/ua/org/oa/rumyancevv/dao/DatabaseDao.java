package ua.org.oa.rumyancevv.dao;

import ua.org.oa.rumyancevv.dao.implJdbc.*;

public class DatabaseDao implements DAO {
    @Override
    public UserDao getUserDao() {
        return new UserDaoSqlImpl();
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoSqlImpl();
    }

    @Override
    public ReportDao getReportDao() {
        return new ReportDaoSqlImpl("report");
    }

    @Override
    public ReportDao getRentsDao() {
        return new ReportDaoSqlImpl("rents");
    }
}
