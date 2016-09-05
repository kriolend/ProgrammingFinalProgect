package ua.org.oa.rumyancevv.dao.implJdbc;


import ua.org.oa.rumyancevv.dao.ReportDao;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.jdbc.SQLs;
import ua.org.oa.rumyancevv.jdbc.provider.ConnectionProvider;
import ua.org.oa.rumyancevv.model.dto.ReportDto;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.model.entity.Report;
import ua.org.oa.rumyancevv.model.entity.User;
import ua.org.oa.rumyancevv.properties.PropertiesUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class ReportDaoSqlImpl implements ReportDao {
    String table;
    public ReportDaoSqlImpl(String table) {
        this.table = table;
    }

    @Override
    public LinkedList<ReportDto> getReportsArchive() throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<ReportDto> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        PreparedStatement preparedStatement;
        ReportDto report;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(SQLs.SELECT_REPORTS_ARCHIVE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String userName = resultSet.getString("name");
                LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
                LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
                report = new ReportDto(id, author, title, userName,rentDate, returnDate);
                result.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public Report selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        Report result = null;
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        PreparedStatement preparedStatement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            if (table.equals("rents")) {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_RENTS_BY_ID);
            } else {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_REPORT_BY_ID);
            }
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                long bookId = resultSet.getLong("bookId");
                long userId = resultSet.getLong("userId");
                LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
                LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
                result = new Report(id, bookId, userId, rentDate, returnDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public LinkedList<Report> getAllReports() throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<Report> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        PreparedStatement preparedStatement;
        Report report;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            if (table.equals("rents")) {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_ALL_RENTS);
            } else {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_ALL_REPORTS);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long bookId = resultSet.getLong("bookId");
                long userId = resultSet.getLong("userId");
                LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
                LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
                report = new Report(id, bookId, userId, rentDate, returnDate);
                result.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public LinkedList<Report> getReportsByUserAndBook(long userId, long bookId) throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<Report> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        PreparedStatement preparedStatement;
        Report report;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            if (table.equals("rents")) {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_RENT_BY_BOOK_AND_USER);
            } else {
                preparedStatement = connection.prepareStatement(SQLs.SELECT_REPORT_BY_BOOK_AND_USER);
            }
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long currBookId = resultSet.getLong("bookId");
                long currUserId = resultSet.getLong("userId");
                LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
                LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
                report = new Report(id, currBookId, currUserId, rentDate, returnDate);
                result.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;

    }

    @Override
    public LinkedList<Report> selectByDate(LocalDate rentDate, LocalDate returnDate) {
        return null;
    }

    @Override
    public LinkedList<User> selectUsersByBook(long bookId) {
        return null;
    }

    @Override
    public LinkedList<Book> selectBooksByUser(long userId) throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<Book> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.SELECT_RENTED_BOOKS_BY_USER);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                int balance = resultSet.getInt("balance");
                Book book = new Book(id, author, title, balance);
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public void insertReport(Report report) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            PreparedStatement preparedStatement;
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            if (table.equals("rents")) {
                preparedStatement = connection.prepareStatement(SQLs.INSERT_RENT);
            } else {
                preparedStatement = connection.prepareStatement(SQLs.INSERT_REPORT);
            }
            preparedStatement.setLong(1, report.getBookId());
            preparedStatement.setLong(2, report.getUserId());
            preparedStatement.setDate(3, Date.valueOf(report.getRentDate()));
            preparedStatement.setDate(4, Date.valueOf(report.getReturnDate()));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void updateReport(long id, Report report) {

    }

    @Override
    public void deleteReport(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        PreparedStatement preparedStatement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            if (table.equals("rents")) {
                preparedStatement = connection.prepareStatement(SQLs.DELETE_RENT);
            } else {
                preparedStatement = connection.prepareStatement(SQLs.DELETE_REPORT);
            }
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
