import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;




public class BookDAO {

    Connection conn = DatabaseConnection.getConnection();

   public void addBook(Book book) {
        // JDBC code to add book to database

        try{
            //Create SQL insert statement
            String sql = "INSERT INTO Books (title, author, genre, published_date, isbn, copies_available) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            //statement.setInt(1, book.getID());
            statement.setString(1, book.get_title());
            statement.setString(2, book.get_author());
            statement.setString(3, book.get_genre());
            statement.setDate(4, book.get_published_date());
            statement.setString(5, book.get_isbn());
            statement.setInt(6, book.get_copiesAvailable());


            // Execute insert statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0)
            {
                System.out.println("A new book was inserted successfully!");
            }

            // Close connection
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void updateBook(Book book) {
        // JDBC code to update book in database

        try {
            // Create SQL update statement


            String sql = "UPDATE Books SET title = ?, author = ?, genre= ?, published_date = ? ,isbn = ? , copies_available = ?  WHERE book_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1,book.get_title());
            statement.setString(2,book.get_author());
            statement.setString(3,book.get_genre());
            statement.setDate(4, book.get_published_date());
            statement.setString(5, book.get_isbn());
            statement.setInt(6, book.get_copiesAvailable());
            statement.setInt(7,book.getID());

            //Execute select statement
            if (statement.executeUpdate() > 0)
            {
                System.out.println("Book was updated successfully !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        // JDBC code to delete book from database

        try {
            // Create SQL statement
            String sql = "DELETE FROM Books WHERE book_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, bookId);

            //Execute statement query
            if (statement.executeUpdate() > 0)
            {
                System.out.println("Book was deleted successfully !");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public Book getBook(int bookId) {
        // JDBC code to get book from database

        try {
            // Create SQL select statement
            String sql = "SELECT * FROM Books WHERE book_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bookId);

            //execute select statement and get the result set
            ResultSet resultSet = statement.executeQuery();


            // get the row 
            if (resultSet.next())
            {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                Date publishedDate = resultSet.getDate("published_date");
                String isbn = resultSet.getString("isbn");
                int copies_available = resultSet.getInt("copies_available");
                return new Book(bookId, title, author, genre, publishedDate, isbn,copies_available);
            }
            else
            {
                System.out.println("NO DATA FOUND");
                return null;
            }
            

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public List<Book> getAllBooks() {
        // JDBC code to get all books from database

        List<Book> list_of_Books = new ArrayList<Book>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try
        {
            //Create SQL select statement
            String sql = "SELECT * FROM Books";
            statement = conn.prepareStatement(sql);

            //Execute select statement and get the result set
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next())
            {
                int book_id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                Date publishedDate = resultSet.getDate("published_date");
                String isbn = resultSet.getString("isbn");
                int copies_available = resultSet.getInt("copies_available");

                list_of_Books.add(new Book(book_id, title, author, genre, publishedDate, isbn,copies_available));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            /*
            //close the resultSet, statement, and connection in reverse order
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

        return list_of_Books;


    }
}
