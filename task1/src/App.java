
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database1";
        String username = "root";
        String password = "123abc";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");
        }
        catch(SQLException e){
            System.out.println("Error in establishing connection: " + e.getMessage());
        }
    }
}