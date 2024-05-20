package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public void fetchData() {
        Connection connection = MyJDBC.getConnection();
        String query = "SELECT * FROM load_level";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idLoadLevelMap = resultSet.getInt("id_load_level_map");
                int posX = resultSet.getInt("posX");
                int posY = resultSet.getInt("posY");
                int burgerCnt = resultSet.getInt("burger_cnt");
                int dishCnt = resultSet.getInt("dish_cnt");
                int saladCnt = resultSet.getInt("salad_cnt");
                int steakCnt = resultSet.getInt("steak_cnt");
                Integer lipieCnt = (Integer) resultSet.getObject("lipie_cnt"); // poate fi NULL
                Integer chickenCnt = (Integer) resultSet.getObject("chicken_cnt"); // poate fi NULL
                Integer burritoCnt = (Integer) resultSet.getObject("burrito_cnt"); // poate fi NULL
                double timer = resultSet.getDouble("timer");

                // Afișează valorile
                System.out.println("ID: " + idLoadLevelMap);
                System.out.println("PosX: " + posX);
                System.out.println("PosY: " + posY);
                System.out.println("Burger Count: " + burgerCnt);
                System.out.println("Dish Count: " + dishCnt);
                System.out.println("Salad Count: " + saladCnt);
                System.out.println("Steak Count: " + steakCnt);
                System.out.println("Lipie Count: " + (lipieCnt != null ? lipieCnt : "NULL"));
                System.out.println("Chicken Count: " + (chickenCnt != null ? chickenCnt : "NULL"));
                System.out.println("Burrito Count: " + (burritoCnt != null ? burritoCnt : "NULL"));
                System.out.println("Timer: " + timer);
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Opțional, închide conexiunea aici sau în clasa DatabaseConnection
            MyJDBC.closeConnection(connection);
        }
    }
}
