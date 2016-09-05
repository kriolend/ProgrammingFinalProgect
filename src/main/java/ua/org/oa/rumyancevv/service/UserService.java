package ua.org.oa.rumyancevv.service;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.exceptions.ServiceException;
import ua.org.oa.rumyancevv.model.dto.UserDto;

import java.sql.SQLException;
import java.util.LinkedList;

public interface UserService {
    long insertUser(UserDto userDto) throws ServiceException, ConnectionProviderException, SQLException, PropertiesException;
    void deleteUser(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    void updateUser(long id, UserDto userDto) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<UserDto> getAllUsers() throws ConnectionProviderException, SQLException, PropertiesException;
    void userRentsBook(long userId, long bookId) throws ServiceException, ConnectionProviderException, SQLException, PropertiesException;
    void userReturnsBook(long userId, long bookId) throws ConnectionProviderException, SQLException, ServiceException, PropertiesException;
}