package ua.org.oa.rumyancevv.service;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.exceptions.ServiceException;
import ua.org.oa.rumyancevv.model.dto.ReportDto;
import ua.org.oa.rumyancevv.model.dto.BookDto;

import java.sql.SQLException;
import java.util.LinkedList;

public interface BookService {
    long insertBook(BookDto bookDto) throws ConnectionProviderException, SQLException, ServiceException, PropertiesException;
    void deleteBook(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    void updateBook(long id, BookDto bookDto) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<BookDto> getAllBooks(int fromBalance) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<BookDto> booksRentedByUser(long userId) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<ReportDto> getArchiveReports() throws ConnectionProviderException, SQLException, PropertiesException;
}