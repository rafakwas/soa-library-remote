package data;

import model.Book;
import utils.IOUtils;

import javax.ejb.*;
import java.util.List;

@Singleton
@Startup
@Remote(BookRepositoryRemote.class)
public class BookRepository implements BookRepositoryRemote {
    private static final String BOOKSTORE_PATH = "bookstore.xml";

    private List<Book> bookList;

    public List<Book> getAllBooks() {
        return IOUtils.getBookstore().getBookList();
    }

    @Lock
    public List<Book> getBookList() {
        return getAllBooks();
    }

    @Override
    public void rent(Book book) {
        if(!book.isReserved()) {
            IOUtils.setRent(book,true);
        }
    }

    @Override
    public void reserve(Book book) {
        IOUtils.modifyReservation(book);
    }

    @Override
    public void returning(Book book) {
        if(book.isRented()) {
            IOUtils.setRent(book,false);
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        }
    }

    @Override
    public void persist(Book book) {
        IOUtils.addBook(book);
    }

        public void removeBookstore() {
            IOUtils.removeBooks();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
}
