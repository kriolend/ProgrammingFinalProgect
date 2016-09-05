package ua.org.oa.rumyancevv.model.dto;

import ua.org.oa.rumyancevv.model.entity.Report;
import ua.org.oa.rumyancevv.model.entity.User;

import java.util.List;

public class BookDto {
    private long id;
    private String author;
    private String title;
    private int balance;
    private List<User> users;
    private List<Report> report;

    public BookDto() {
    }

    public BookDto(long id, String author, String title, int balance) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.balance = balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", balance=" + balance +
                ", users=" + users +
                ", report=" + report +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getBalance() {
        return balance;
    }
}
