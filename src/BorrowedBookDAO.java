import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;

public class BorrowedBookDAO {

    Connection conn = DatabaseConnection.getConnection();
    BookService serviceBookObject = new BookService();
    MemberService serviceMemberObject = new MemberService();


    public void borrowBook(int bookID, int memberID, Date issueDate, Date returnDate) {
            // JDBC code to add borrowedbook to database

            try {
                // Create SQL insert statement
                String sql = "INSERT INTO BorrowedBooks (book_id, member_id, issue_date, return_date, fine) VALUES (?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1, bookID);
                statement.setInt(2, memberID);
                statement.setDate(3, issueDate);
                statement.setDate(4, returnDate);
                statement.setInt(5, 0);

                // Execute INSERT statment

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0)
                {
                    System.out.println("Borrowed book recorded");
                }

                //close connection
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public void updateBorrowedBook(int bookID) {
            // JDBC code to update borrowed book in database
        }

        public void deleteBorrowedBook(int bookId, int memberID) {
            // JDBC code to delete borrowed book from database

            try {
                 // Create SQL delete statement
                String sql =  "DELETE FROM BorrowedBooks WHERE book_id = ? AND member_id = ? ";
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1, bookId);
                statement.setInt(2, memberID);

                // execute the query

                if (statement.executeUpdate() > 0)
                {
                    System.out.println("Book returned");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    
        public Book getBorroweBook(int bookId) {
            // JDBC code to get borrowed book from database
           
            return serviceBookObject.getBook(bookId);
        }
        

        public HashMap<String, Book> getAllBorrowedBooks() {
            // JDBC code to get all borrowed books from database
            
            HashMap<String, Book> borrowedBooks = new HashMap<String, Book>();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                // Create SQL select statement
                String sql = "SELECT * FROM BorrowedBooks";
                statement = conn.prepareStatement(sql);

                // Execute select statement and get the result set
                resultSet = statement.executeQuery();
                
                // Get the results
                while(resultSet.next())
                {
                    borrowedBooks.put(serviceMemberObject.getMember(resultSet.getInt("member_id")).get_name(), serviceBookObject.getBook(resultSet.getInt("book_id")));
                }
                statement.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            //close 
            

            return borrowedBooks;
        }
}

