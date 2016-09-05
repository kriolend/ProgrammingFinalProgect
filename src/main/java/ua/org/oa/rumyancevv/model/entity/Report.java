package ua.org.oa.rumyancevv.model.entity;


import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable {
    private long id;
    private long bookId;
    private long userId;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Report() {
    }

    public Report(long id, long bookId, long userId, LocalDate rentDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("%17s","Report {" + "bookId=" + bookId) +
                String.format("%23s","userId=" + userId) +
                String.format("%23s", "rentDate=" + rentDate) +
                String.format("%23s", "returnDate=" + returnDate) +'}'+"\n";
    }

    public long getBookId() {
        return bookId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public long getId() {
        return id;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
