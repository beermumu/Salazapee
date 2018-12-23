package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminItemDB {

    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static void saveItem(String id, String type , String name, int quantity , int cost , String description) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Item (ID, Type, Name, Quantity, Cost, Description) values " +
                        "('" + id + "' , '" + type + "' , '" + name  + "' , '" + quantity + "' , '" + cost + "' , '" + description + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
