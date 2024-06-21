import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;


public class MemberDAO {

    Connection conn = DatabaseConnection.getConnection();

    public void addMember(Member member) {
        // JDBC code to add member to database
        try{
            //Create SQL insert statement
            String sql = "INSERT INTO Members (member_id, name, address, phone, email, membership_date) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, member.get_id());
            statement.setString(2, member.get_name());
            statement.setString(3, member.get_address());
            statement.setString(4, member.get_phone());
            statement.setString(5, member.get_email());
            statement.setDate(6, member.get_membershipDate());


            // Execute insert statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0)
            {
                System.out.println("A new member was inserted successfully!");
            }

            // Close connection
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateMember(Member member) {
        // JDBC code to update member in database

        try {
            // Create SQL update statement


            String sql = "UPDATE Members SET name = ?, address = ?, phone = ?, email = ?  WHERE member_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, member.get_name());
            statement.setString(2, member.get_address());
            statement.setString(3, member.get_phone());
            statement.setString(4, member.get_email());

            //Execute select statement
            if (statement.executeUpdate() > 0)
            {
                System.out.println("Member was updated successfully !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int memberID) {
        // JDBC code to delete member from database

        try {
            // Create SQL statement
            String sql = "DELETE FROM Members WHERE member_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, memberID);

            //Execute statement query
            if (statement.executeUpdate() > 0)
            {
                System.out.println("Member was deleted successfully !");

            }
            else
            {
                System.out.println("Member ID "+ memberID+" does not exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getMember(int memberID) {
        // JDBC code to get member from database
        try {
            // Create SQL select statement
            String sql = "SELECT * FROM Members WHERE member_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, memberID);

            //execute select statement and get the result set
            ResultSet resultSet = statement.executeQuery();


            // get the row 
            if (resultSet.next())
            {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Date memberShipDate = resultSet.getDate("membership_date");

                return new Member(memberID, name, address, phone, email, memberShipDate);
            }
            else
            {
                System.out.println("Member Does Not Exists !!!");
                return null;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> getAllMembers() {
        // JDBC code to get all members from database

        List<Member> list_of_members = new ArrayList<Member>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try
        {
            //Create SQL select statement
            String sql = "SELECT * FROM Members";
            statement = conn.prepareStatement(sql);

            //Execute select statement and get the result set
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next())
            {
                int memberID = resultSet.getInt("member_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Date memberShipDate = resultSet.getDate("membership_date");

                list_of_members.add(new Member(memberID, name, address, phone, email, memberShipDate));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list_of_members;
    }
}
