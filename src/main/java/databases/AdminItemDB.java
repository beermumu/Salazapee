package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
    public static ObservableList loadItem() {
        ObservableList<RegisterSubject> accounts = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Register";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String name = resultSet.getString("Name");
                    int credit = resultSet.getInt("Credit");
                    accounts.add(new RegisterSubject(id,name,credit));
                }
                statement.close();
                resultSet.close();
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }



}
