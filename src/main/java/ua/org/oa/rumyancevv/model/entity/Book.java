package ua.org.oa.rumyancevv.model.entity;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private long id;
    private String author;
    private String title;
    private int balance;
    private List<User> users;
    private List<Report> report;

    public Book() {
    }

    public Book(long id) {
        this.id = id;
    }

    public Book(long id, String author, String title, int balance) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.balance = balance;
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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("%20s", author + '\'') +
                String.format("%30s", title + '\'') +
                String.format("%22s","balance=" + balance) +
                String.format("%22s","users=" + users) +
                String.format("%22s","report=" + report +'}'+'\n');
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
