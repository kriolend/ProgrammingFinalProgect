package ua.org.oa.rumyancevv.service.impl;

import ua.org.oa.rumyancevv.dao.BookDao;
import ua.org.oa.rumyancevv.dao.ReportDao;
import ua.org.oa.rumyancevv.dao.UserDao;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.exceptions.ServiceException;
import ua.org.oa.rumyancevv.model.dto.UserDto;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.model.entity.Report;
import ua.org.oa.rumyancevv.model.entity.User;
import ua.org.oa.rumyancevv.service.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import static ua.org.oa.rumyancevv.transform.Transformer.userFromDto;
import static ua.org.oa.rumyancevv.transform.Transformer.userToDto;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BookDao bookDao;
    private ReportDao reportDao;
    private ReportDao rentsDao;

    public UserServiceImpl(UserDao userDao, BookDao bookDao, ReportDao reportDao, ReportDao rentsDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.reportDao = reportDao;
        this.rentsDao = rentsDao;
    }

    @Override
    public long insertUser(UserDto userDto) throws ServiceException, ConnectionProviderException, SQLException, PropertiesException {
        User user = userFromDto(userDto);
        LinkedList<User> users = userDao.getAllUsers();
        for (User userFromList : users) {
            if (userFromList.equals(user)) {
                throw new ServiceException("User already exsists");
            }
        }
        long result = userDao.insertUser(user);
        return result;
    }

    @Override
    public void deleteUser(long id) throws ConnectionProviderException, SQLException, PropertiesException {
        userDao.deleteUser(id);
    }

    public void updateUser(long id, UserDto userDto) throws ConnectionProviderException, SQLException, PropertiesException {
        User user = userFromDto(userDto);
        userDao.updateUser(id, user);
    }

    @Override
    public LinkedList<UserDto> getAllUsers() throws ConnectionProviderException, SQLException, PropertiesException {
        LinkedList<UserDto> result = new LinkedList<>();
        LinkedList<User> users = userDao.getAllUsers();
        for (User user : users) {
            result.add(userToDto(user));
        }
        return result;
    }

    @Override
    public void userRentsBook(long userId, long bookId) throws ServiceException, ConnectionProviderException, SQLException, PropertiesException {
        Book book = bookDao.selectById(bookId);
        if (book.getId() == -1) {
            throw new ServiceException("Wrong book number");
        }
        LinkedList<Report> reports = rentsDao.getReportsByUserAndBook(userId, bookId);
        if (reports.size() > 0) {
            throw new ServiceException("You already have this book");
        } else {
            if (book.getBalance() > 0) {
                Report report = new Report(0, bookId, userId, LocalDate.now(), LocalDate.now());
                rentsDao.insertReport(report);
                bookDao.addToStock(bookId, -1);
            } else {
                throw new ServiceException("Out of stock");
            }
        }
    }

    @Override
    public void userReturnsBook(long userId, long bookId) throws ConnectionProviderException, SQLException, ServiceException, PropertiesException {
        LinkedList<Report> reports = rentsDao.getReportsByUserAndBook(userId, bookId);
        if (reports.size() > 0) {
            Long reportId = reports.getFirst().getId();
            Report report = rentsDao.selectById(reportId);
            rentsDao.deleteReport(reportId);
            bookDao.addToStock(bookId, 1);
            LocalDate date = LocalDate.now();
            report.setReturnDate(date);
            reportDao.insertReport(report);
        } else {
            throw new ServiceException("This user does not have such book");
        }
    }
}
