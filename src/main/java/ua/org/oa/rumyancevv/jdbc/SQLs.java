package ua.org.oa.rumyancevv.jdbc;

public class SQLs {
    public static final String INSERT_USER = "INSERT INTO user (name, login, password, birthday) VALUES (?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE user SET name = ?, login = ?, password = ?, birthday = ? where id = ?;";
    public static final String SELECT_ALL_USERS = "SELECT id, name, login, password, birthday FROM user";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    public static final String SELECT_USER_BY_ID = "SELECT id, name, login, password, birthday from user where id=?";

    public static final String INSERT_BOOK = "INSERT INTO book (author, title, balance) VALUES (?, ?, ?)";
    public static final String UPDATE_BOOK = "UPDATE book SET author = ?, title = ?, balance = ? where id = ?;";
    public static final String SELECT_ALL_BOOKS_WITH_BALANCE = "SELECT id, author, title, balance FROM book WHERE balance >= ?";
    public static final String DELETE_BOOK = "DELETE FROM book WHERE id = ?";
    public static final String SELECT_BOOK_BY_ID = "SELECT id, author, title, balance FROM book where id=?";
    public static final String ADD_BOOK_TO_STOCK = "UPDATE book SET balance = balance + ? where id=?";

    public static final String SELECT_RENTS_BY_ID = "SELECT id, bookId, userId, rentDate, returnDate from rents where id = ?";
    public static final String SELECT_RENT_BY_BOOK_AND_USER =
            "SELECT id, bookId, userId, rentDate, returnDate from rents where userId = ? AND bookId = ?";
    public static final String SELECT_ALL_RENTS = "SELECT id, bookId, userId, rentDate, returnDate from rents";
    public static final String SELECT_RENTED_BOOKS_BY_USER = "SELECT book.id, book.author, book.title, book.balance FROM book\n" +
            "INNER JOIN rents ON book.id = rents.bookId\n" +
            "WHERE rents.userId = ?;";
    public static final String INSERT_RENT = "INSERT INTO rents (bookId, userId, rentDate, returnDate) VALUES (?, ?, ?, ?);";
    public static final String DELETE_RENT = "DELETE FROM rents WHERE id = ?";

    public static final String SELECT_REPORT_BY_ID = "SELECT id, bookId, userId, rentDate, returnDate from report where id = ?";
    public static final String INSERT_REPORT = "INSERT INTO report (bookId, userId, rentDate, returnDate) VALUES (?, ?, ?, ?);";
    public static final String SELECT_ALL_REPORTS = "SELECT id, bookId, userId, rentDate, returnDate from report";
    public static final String SELECT_REPORT_BY_BOOK_AND_USER =
            "SELECT id, bookId, userId, rentDate, returnDate from report where userId = ? AND bookId = ?";
    public static final String DELETE_REPORT = "DELETE FROM report WHERE id = ?";
    public static final String SELECT_REPORTS_ARCHIVE =
            "SELECT report.id, book.author, book.title, user.name, report.rentDate, report.returnDate\n" +
            "from report\n" +
            "inner join book on report.bookId = book.id\n" +
            "inner join user on report.userId = user.id " +
                    "order by returnDate desc, book.author asc;";
}
