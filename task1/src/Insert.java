
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Insert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database1";
        String username = "root";
        String password = "123abc";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");

            Statement statement = connection.createStatement();
            
            statement.execute("CREATE TABLE IF NOT EXISTS students (id int primary key, name varchar(50), age int)");
            
            Scanner input = new Scanner(System.in);
            System.out.println("Enter student id: ");
            int id = input.nextInt();
            System.out.println("Enter student name: ");
            String name = input.next();
            System.out.println("Enter student age: ");
            int age = input.nextInt();
           
            statement.execute("INSERT INTO students (id, name, age) VALUES (" + id + ", '" + name + "', " + age + ")");
            
            System.out.println("Student details inserted successfully");
            // Task 5: Delete student details
            ResultSet results = statement.executeQuery("SELECT * FROM students");
            while (results.next()) {
                System.out.println("ID: " + results.getInt("id") + ", Name: " + results.getString("name") + ", Age: "
                        + results.getInt("age"));
                
            }
            System.out.println("Enter student id to delete: ");
            int idToDelete = input.nextInt();
            statement.execute("DELETE FROM students WHERE id = " + idToDelete);
            System.out.println("Student details deleted successfully");
            results = statement.executeQuery("SELECT * FROM students");
            while (results.next()) {
                System.out.println("ID: " + results.getInt("id") + ", Name: " + results.getString("name") + ", Age: "
                        + results.getInt("age"));
            }

        }
        catch(SQLException e){
            System.out.println("Error in establishing connection: " + e.getMessage());
        }

        
    }
}