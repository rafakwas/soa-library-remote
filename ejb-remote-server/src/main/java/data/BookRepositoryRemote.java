package data;

import model.Book;
import utils.IOUtils;

import java.util.List;

public interface BookRepositoryRemote {
    List<Book> getAllBooks();
    void rent(Book book);
    void reserve(Book book);
    void returning(Book book);
    void persist(Book book);
    void removeBookstore();
}
