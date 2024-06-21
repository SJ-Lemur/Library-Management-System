import java.util.List;

public class BookService {
    
    private BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }

    public Book getBook(int bookId) {
        return bookDAO.getBook(bookId);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
}

