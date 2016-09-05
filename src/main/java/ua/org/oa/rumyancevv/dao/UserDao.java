package ua.org.oa.rumyancevv.dao;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.model.entity.User;

import java.sql.SQLException;
import java.util.LinkedList;

public interface UserDao {
    User selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    User selectByIdWithBooks(long id);
    long insertUser(User user) throws ConnectionProviderException, SQLException, PropertiesException;
    void updateUser(long id, User user) throws ConnectionProviderException, SQLException, PropertiesException;
    void deleteUser(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<User> getAllUsers() throws ConnectionProviderException, SQLException, PropertiesException;
}
