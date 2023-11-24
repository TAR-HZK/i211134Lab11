import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {
    public static void main(String[] args) {
        String username = "employee_user";
        String password = "Securepassword@123";
        String url = "jdbc:mysql://localhost:3306/connection";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false); // Start a transaction

            try (PreparedStatement createTable = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS accounts (id int primary key, name varchar(50), balance int , currentAmount int)")) {
                createTable.execute();
            }

            try (PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO accounts (id, name, balance, currentAmount) VALUES (?, ?, ?, ?)")) {
                insert.setInt(1, 1);
                insert.setString(2, "John Doe");
                insert.setInt(3, 1000);
                insert.setInt(4, 500);
                insert.execute();
            }

            connection.commit(); 
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback(); 
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}