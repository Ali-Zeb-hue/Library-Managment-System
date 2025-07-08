import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQueryExecutor {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id") + 
                                   ", Title: " + rs.getString("title") + 
                                   ", Author: " + rs.getString("author") + 
                                   ", Publisher: " + rs.getString("publisher") + 
                                   ", Year: " + rs.getString("year") + 
                                   ", ISBN: " + rs.getString("isbn") + 
                                   ", Copies: " + rs.getString("copies"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}