package ua.org.oa.rumyancevv.dao;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;
import ua.org.oa.rumyancevv.model.dto.ReportDto;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.model.entity.Report;
import ua.org.oa.rumyancevv.model.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public interface ReportDao {
    Report selectById(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<Report> getAllReports() throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<Report> getReportsByUserAndBook(long userId, long bookId) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<Report> selectByDate(LocalDate rentDate, LocalDate returnDate);
    LinkedList<User> selectUsersByBook(long bookId);
    LinkedList<Book> selectBooksByUser(long userId) throws ConnectionProviderException, SQLException, PropertiesException;
    void insertReport(Report report) throws ConnectionProviderException, SQLException, PropertiesException;
    void updateReport(long id, Report report);
    void deleteReport(long id) throws ConnectionProviderException, SQLException, PropertiesException;
    LinkedList<ReportDto> getReportsArchive() throws ConnectionProviderException, SQLException, PropertiesException;
}
