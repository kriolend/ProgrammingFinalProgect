package ua.org.oa.rumyancevv.model.dto;

import ua.org.oa.rumyancevv.model.entity.Book;

import java.time.LocalDate;
import java.util.List;

public class UserDto {
    private long id;
    private String name;
    private String login;
    private String password;
    private LocalDate birthday;
    private List<Book> books;

    public UserDto(long id, String name, String login, String password, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
    }

    public UserDto(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public UserDto() {
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%10s", "User {" + "id='" + id + '\'') +
                String.format("%18s", "name='" + name + '\'') +
                String.format("%18s", "login='" + login + '\'') +
                String.format("%18s", "password='" + password + '\'') +
                String.format("%22s", "birthday=" + birthday) +
                String.format("%15s", "books=" + books + '}' + '\n');
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public List<Book> getBooks() {
        return books;
    }
}
