package ua.org.oa.rumyancevv.service.impl;

import ua.org.oa.rumyancevv.dao.BookDao;
import ua.org.oa.rumyancevv.dao.ReportDao;
import ua.org.oa.rumyancevv.dao.UserDao;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.exceptions.ServiceException;
import ua.org.oa.rumyancevv.model.dto.ReportDto;
import ua.org.oa.rumyancevv.model.dto.BookDto;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.service.BookService;

import java.sql.SQLException;
import java.util.LinkedList;

import static ua.org.oa.rumyancevv.transform.Transformer.bookFromDto;
import static ua.org.oa.rumyancevv.transform.Transformer.bookToDto;

public class BookServiceImpl implements BookService {
    private UserDao userDao;
    private BookDao bookDao;
    private ReportDao reportDao;
    private ReportDao rentsDao;

    public BookServiceImpl(UserDao userDao, BookDao bookDao, ReportDao reportDao, ReportDao rentsDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.reportDao = reportDao;
        this.rentsDao = rentsDao;
    }


    @Override
    public long insertBook(BookDto bookDto) throws ConnectionProviderException, SQLException, ServiceException, PropertiesException {
        Book book = bookFromDto(bookDto);
        LinkedList<Book> books = bookDao.getAllBooks(0);
        for (Book bookFromList : books) {
            if (bookFromList.getTitle().equals(book.getTitle())) {
                throw new ServiceException("Book already exsists");
            }
        }
        return bookDao.insertBook(book);
    }

    @Override
    public void deleteBook(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(long id, BookDto bookDto) throws ConnectionProviderException, SQLException, PropertiesException {
        Book book = bookFromDto(bookDto);
        bookDao.updateBook(id, book);
    }

    @Override
    public LinkedList<BookDto> getAllBooks(int fromBalance) throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<BookDto> result = new LinkedList<>();
        LinkedList<Book> books = bookDao.getAllBooks(fromBalance);
        for (Book book : books) {
            result.add(bookToDto(book));
        }
        return result;
    }

    @Override
    public LinkedList<BookDto> booksRentedByUser(long userId) throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<BookDto> result = new LinkedList<>();
        LinkedList<Book> books;
        books = rentsDao.selectBooksByUser(userId);
            for (Book book : books) {
                result.add(bookToDto(book));
            }
        return result;
    }

    @Override
    public LinkedList<ReportDto> getArchiveReports() throws ConnectionProviderException, SQLException, PropertiesException {
        return reportDao.getReportsArchive();
    }
}
