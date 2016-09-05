package ua.org.oa.rumyancevv.exceptions;

import javax.servlet.ServletException;

public class LibraryException extends ServletException {
    public LibraryException(String message) {
        super(message);
    }
}
