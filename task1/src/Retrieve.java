
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Retrieve {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/connection";
        String username = "employee_user";
        String password = "Securepassword@123";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS employees (id int primary key, name varchar(50), age int)");

            Scanner input = new Scanner(System.in);
            System.out.println("Enter employee id: ");
            int id = input.nextInt();
            System.out.println("Enter employee name: ");
            String name = input.next();
            System.out.println("Enter employee age: ");
            int age = input.nextInt();

            statement.execute("INSERT INTO employees (id, name, age) VALUES (" + id + ", '" + name + "', " + age + ")");

            System.out.println("Employee details inserted successfully");

            ResultSet results = statement.executeQuery("SELECT * FROM employees");

            while (results.next()) {
                System.out.println("ID: " + results.getInt("id") + ", Name: " + results.getString("name") + ", Age: "
                        + results.getInt("age"));
            }

            //Task 4: Update employee details

            System.out.println("Enter employee id to update: ");
            int idToUpdate = input.nextInt();
            System.out.println("Enter employee new age: ");
            int newAge = input.nextInt();

            statement.execute("UPDATE employees SET age = " + newAge + " WHERE id = " + idToUpdate);

            System.out.println("Employee details updated successfully");
            
            results = statement.executeQuery("SELECT * FROM employees");

            while (results.next()) {
                System.out.println("ID: " + results.getInt("id") + ", Name: " + results.getString("name") + ", Age: "
                        + results.getInt("age"));
            }
            


        } catch (SQLException e) {
            System.out.println("Error in establishing connection: " + e.getMessage());
        }
    }
}
