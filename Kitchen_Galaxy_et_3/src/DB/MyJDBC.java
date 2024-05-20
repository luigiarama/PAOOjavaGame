package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "galaxy23";

    // Metodă pentru a obține o nouă conexiune la baza de date
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Stabilește conexiunea
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Metodă pentru a închide conexiunea
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}