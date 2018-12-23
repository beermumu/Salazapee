package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Item;

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
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Item";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String type = resultSet.getString("Type");
                    String name = resultSet.getString("Name");
                    int quantity = resultSet.getInt("Quantity");
                    int cost = resultSet.getInt("Cost");
                    String description = resultSet.getString("Description");
                    items.add(new Item(id,type,name,quantity,cost,description));
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
        return items;
    }

    public static void editItem(String id, String type , String name, int quantity , int cost , String description) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Item set ID = '" + id + "' , " +
                        "Type = '" + type + "' , Name = '" + name + "' , Quantity = '" + quantity + "' , Cost = '" + cost +"' " +
                        ", Description = '" + description + "' where Subject.ID =='" + id + "'";
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

    public static void deleteItem(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Item where ID == '" + id + "'";
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