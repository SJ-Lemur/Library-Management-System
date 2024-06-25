import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class BorrowedBookService {
     private BorrowedBookDAO dbAccessObject = new BorrowedBookDAO();

    public void borrowBook(int bookID, int memberID, Date issueDate, Date returnDate){
        dbAccessObject.borrowBook(bookID, memberID, issueDate, returnDate);
    }

    public void updateBorrowedBook(int bookID){
        dbAccessObject.updateBorrowedBook(bookID);
    }

    public void deleteBorrowedBook(int bookId, int memberID) {
        dbAccessObject.deleteBorrowedBook(bookId, memberID);
    }

    public Book getBorroweBook(int bookId) {
        return dbAccessObject.getBorroweBook(bookId);
    }

    public HashMap<String, Book> getAllBorrowedBooks(){
        return dbAccessObject.getAllBorrowedBooks();
    }
}
