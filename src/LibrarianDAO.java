import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

public class LibrarianDAO {
    Connection conn = DatabaseConnection.getConnection();

   public void addLibrarian(Librarian librarian) {
        // JDBC code to add librarian to database

        try{
             // Create SQL insert statement
             String sql = "INSERT INTO Librarians (name, username, password) VALUES (?,?,?)";
             PreparedStatement statement = conn.prepareStatement(sql);

             statement.setString(1,librarian.get_name());
             statement.setString(2, librarian.get_username());
             statement.setString(3, librarian.get_password());

             //Execute statement

             if (statement.executeUpdate() > 0)
             {
                System.out.println("A new librarian details have been recorded successfully.");
             }

             // Close connection
             statement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateLibrarianDetails(Librarian librarian) {
        // JDBC code to update librarian in database

        try {
            // Create SQL update statement
            String sql = "UPDATE Librarians SET name=?, username=?, password=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, librarian.get_name());
            statement.setString(2, librarian.get_username());
            statement.setString(3, librarian.get_password());

            //execute statement

            if (statement.executeUpdate() > 0)
            {
                System.out.println("Librarian's details updated successfully.");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLibrarian(String username, String password) {
        // JDBC code to delete librarian from database

        try {
            // Define SQL delete statement
            String sql = "DELETE FROM Librarians WHERE username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);

            // execute statement

            if (statement.executeUpdate() > 0)
            {
                System.out.println("Librarian deleted successfully.");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
 
    public Librarian getLibrarian(String username, String password) {
        // JDBC code to get librarian from database
        String name = null;
        int librarina_id = -1;

        try {
            // Define SQL statement
            String sql = "SELECT * FROM librarians WHERE username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);
            // Execute select statement and get the result set

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                name = resultSet.getString("name");
                librarina_id = resultSet.getInt("librarian_id");
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Librarian(librarina_id, name, username, password);
    }
    
}
