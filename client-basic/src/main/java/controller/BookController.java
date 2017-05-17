package controller;

import data.BookRepositoryRemote;
import model.Book;
import model.Pair;
import utils.Initializer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean
@ViewScoped
@Named("bookcontroller")
public class BookController {

    BookRepositoryRemote bookRepositoryRemote;

    @PostConstruct
    public void init() {
        try {
            bookRepositoryRemote = Initializer.lookupRemoteStatefullLibrary();
            System.out.println("BOOK REPOSITORY INITIALIZED");
        }catch (NamingException e) {
            e.printStackTrace();
            System.out.println("JNDI Naming exception: " + e.getMessage());
        }
    }

    private Book book;
    private List<Book> books;

    private String output;

    private List<String> authors;
    private List<Pair> titles;
    private String isbn;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addBook() {
        book = new Book();
//        if (bookRepositoryRemote.getAllBooks() == null) book.setId(0); else book.setId(bookRepositoryRemote.getAllBooks().size());
        book.setTitleList(Arrays.asList(new Pair("polski", "rzeka"), new Pair("angielski", "river")));
        book.setAuthorList(Arrays.asList("Andrzej Kołodziejko", "Bisurman Tatrzański", "Hędycwoż Międzyrzecki"));
        book.setIsbn("123-456-789");
        book.setReserved(false);
        book.setRented(false);
        System.out.println("Książka dodana");
        System.out.println(book);
        bookRepositoryRemote.persist(book);
    }

    public List<Book> getBooks() {
        List<Book> bookList = bookRepositoryRemote.getAllBooks();
        if (bookList != null && !bookList.isEmpty()) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    Integer id1 = o1.getId();
                    Integer id2 = o2.getId();
                    return id1.compareTo(id2);
                }
            });
        }
        return bookList;
    }

    public void reserveBook(Book book) {
        bookRepositoryRemote.reserve(book);
    }


    public void rentBook(Book book) {
        bookRepositoryRemote.rent(book);
    }

    public void returning(Book book) {
        bookRepositoryRemote.returning(book);
    }

    public void removeBooks() {
        bookRepositoryRemote.removeBookstore();
    }

}