import java.sql.*;
import java.util.Scanner;

public class SqlApp {

    public static void main(String[] args) {

        // define connection parameters
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root"; // find user name
        String password = "rootroot"; // find password

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a SQL query: ");
        String command = scanner.nextLine();

        // establish connection to mysql database
        // try with resources automatically closes connection if data type implements AutoClosable interface
        try (Connection connection = DriverManager.getConnection(url, user, password);) {

            // create a statement object for executing sql queries
            Statement statement = connection.createStatement();


            // Example query : "SELECT * from Products;"
            // execute a query to select all records from prods
            statement.executeQuery(command);

            // get the resultSet object which holds the result of the query
            ResultSet resultSet = statement.getResultSet();

            // iterate through the result set to process each row of results
            while(resultSet.next()) {

                // get value of ProductName column as a string
                String productName = resultSet.getNString("ProductName");

                // get value of UnitPrice column as a String
                // getString can be used to get any type of column
                String unitPrice = resultSet.getString("UnitPrice");

                // If you wanted to treat "UnitPrice" as a numeric value (e.g., for calculations),
                // you could use getDouble() instead of getString(). This would return the value as a Double.
                // For example:
                //Double UnitPrice = resultSet.getDouble("UnitPrice");

                // Print values of productName and UnitPrice for each row
                System.out.println(productName + " " + unitPrice);
            }

        } catch (SQLException e) {
            // handle exceptions
            throw new RuntimeException(e);
        }



    }

}
