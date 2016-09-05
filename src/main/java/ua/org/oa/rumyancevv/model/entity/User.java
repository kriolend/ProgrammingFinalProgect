package ua.org.oa.rumyancevv.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class User implements Serializable {
    private long id;
    private String name;
    private String login;
    private String password;
    private LocalDate birthday;
    private List<Book> books;

    @Override
    public String toString() {
        return String.format("%15s", name + '\'') +
                String.format("%18s", "login='" + login + '\'') +
                String.format("%18s", "password='" + password + '\'') +
                String.format("%22s", "birthday=" + birthday) +
                String.format("%15s", "books=" + books + '}'+'\n');
    }

    public User(long id, String name, String login, String password, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
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

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getBirthday().equals(user.getBirthday());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
