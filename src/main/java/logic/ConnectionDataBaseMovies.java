package logic;

import java.sql.*;

public class ConnectionDataBaseMovies {
    private static final String host = "jdbc:mysql://localhost:3306/my_movies";
    private static final String uName = "root";
    private static final String uPass = "root";

    public static Connection createConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // загружает драйвер из mysql This forces the driver to register itself, so that Java knows how to handle those database connection strings.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver problem");
        }

        try {
            return DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("(method doConnection()) SQLException: " + throwables.getMessage());
            return null;
        }

    }

}
