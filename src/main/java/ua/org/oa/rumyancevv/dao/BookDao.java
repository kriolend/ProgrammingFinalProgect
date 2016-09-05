package ua.org.oa.rumyancevv.dao;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.model.entity.Book;

import java.sql.SQLException;
import java.util.LinkedList;

public interface BookDao {
    Book selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    Book selectByIdWithUsers(long id);
    LinkedList<Book> selectByAuthor(String author);
    long insertBook(Book book) throws ConnectionProviderException, SQLException, PropertiesException;
    void updateBook(long id, Book book) throws ConnectionProviderException, SQLException, PropertiesException;
    void deleteBook(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<Book> getAllBooks(int fromBalance) throws ConnectionProviderException, SQLException, PropertiesException;
    void addToStock(long id, int amount) throws ConnectionProviderException, SQLException, PropertiesException;
}
