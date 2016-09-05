package ua.org.oa.rumyancevv.dao.implJdbc;

import ua.org.oa.rumyancevv.dao.UserDao;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.jdbc.SQLs;
import ua.org.oa.rumyancevv.jdbc.provider.ConnectionProvider;
import ua.org.oa.rumyancevv.model.entity.User;
import ua.org.oa.rumyancevv.properties.PropertiesUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class UserDaoSqlImpl implements UserDao {
    @Override
    public User selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        User result = null;
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String psw = resultSet.getString("password");
                LocalDate birthday = LocalDate.parse(resultSet.getString("birthday"));
                result = new User(id, name, login, psw, birthday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    public User selectByIdWithBooks(long id) {
        return null;
    }

    @Override
    public long insertUser(User user) throws ConnectionProviderException, SQLException, PropertiesException {
        long insertedUserId = 0;
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
            preparedStatement.executeUpdate();
            ResultSet generatedId = preparedStatement.getGeneratedKeys();
            if (generatedId.next()) {
                insertedUserId = generatedId.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return insertedUserId;
    }

    @Override
    public void updateUser(long id, User user) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    @Override
    public void deleteUser(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLs.DELETE_USER);
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
    public LinkedList<User> getAllUsers() throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<User> result = new LinkedList<>();
        String dbSchema = PropertiesUtils.getProperties().getLibraryProperty("JDBC_SCHEMA");
        Connection connection = ConnectionProvider.getConnection(dbSchema);
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLs.SELECT_ALL_USERS);
            //connection.commit();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String psw = resultSet.getString("password");
                LocalDate birthday = LocalDate.parse(resultSet.getString("birthday"));
                User user = new User(id, name, login, psw, birthday);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }
}
