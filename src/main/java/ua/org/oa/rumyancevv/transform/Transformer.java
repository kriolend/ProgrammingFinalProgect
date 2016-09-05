package ua.org.oa.rumyancevv.transform;

import ua.org.oa.rumyancevv.model.dto.BookDto;
import ua.org.oa.rumyancevv.model.dto.UserDto;
import ua.org.oa.rumyancevv.model.entity.Book;
import ua.org.oa.rumyancevv.model.entity.User;

public class Transformer {
    public static User userFromDto (UserDto userDto) {
        User user = new User(userDto. getId(), userDto.getName(), userDto.getLogin(), userDto.getPassword(), userDto.getBirthday());
        return user;
    }

    public static UserDto userToDto (User user) {
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getBirthday());
        return userDto;
    }
    public static Book bookFromDto (BookDto bookDto) {
        Book book = new Book(bookDto.getId(), bookDto.getAuthor(), bookDto.getTitle(), bookDto.getBalance());
        return book;
    }

    public static BookDto bookToDto (Book book) {
        BookDto bookDto = new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getBalance());
        return bookDto;
    }
}
