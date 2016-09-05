package ua.org.oa.rumyancevv.dao.implJdbc;

import ua.org.oa.rumyancevv.dao.BookDao;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.jdbc.SQLs;
import ua.org.oa.rumyancevv.jdbc.provider.ConnectionProvider;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.properties.PropertiesUtils;

import java.sql.*;
import java.util.LinkedList;

public class BookDaoSqlImpl implements BookDao {
    @Override
    public Book selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        Book result = new Book(-1);
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.SELECT_BOOK_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                int balance = resultSet.getInt("balance");
                result = new Book(id, author, title, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public Book selectByIdWithUsers(long id) {
        return null;
    }

    @Override
    public LinkedList<Book> selectByAuthor(String author) {
        return null;
    }

    @Override
    public long insertBook(Book book) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        long insertedBookId = 0;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.INSERT_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getBalance());
            preparedStatement.executeUpdate();
            ResultSet generatedId = preparedStatement.getGeneratedKeys();
            if (generatedId.next()){
                insertedBookId = generatedId.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return insertedBookId;
    }

    @Override
    public void updateBook(long id, Book book) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.UPDATE_BOOK);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getBalance());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void deleteBook(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.DELETE_BOOK);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public LinkedList<Book> getAllBooks(int fromBalance) throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<Book> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.SELECT_ALL_BOOKS_WITH_BALANCE);
            preparedStatement.setInt(1, fromBalance);
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
    public void addToStock(long id, int amount) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.ADD_BOOK_TO_STOCK);
            preparedStatement.setInt(1, amount);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
