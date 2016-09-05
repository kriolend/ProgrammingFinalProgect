package ua.org.oa.rumyancevv.model.dto;

import java.time.LocalDate;

public class ReportDto {
    private long id;
    private String author;
    private String title;
    private String userName;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public ReportDto(long id, String author, String title, String userName, LocalDate rentDate, LocalDate returnDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.userName = userName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
