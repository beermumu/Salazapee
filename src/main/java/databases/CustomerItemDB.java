package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Item;

import java.sql.*;
import java.util.ArrayList;

public class CustomerItemDB {
    public ArrayList loadData() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
                String query = "select * from Item";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int quantity = resultSet.getInt(4);
                    int cost = resultSet.getInt(5);
                    String description = resultSet.getString(6);
                    items.add(new Item(id, type, name, quantity, cost, description));
                }
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ObservableList<Item> loadDataOsv() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
                String query = "select * from Item";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int quantity = resultSet.getInt(4);
                    int cost = resultSet.getInt(5);
                    String description = resultSet.getString(6);
                    items.add(new Item(id, type, name, quantity, cost, description));
                }
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void updateQtyItem(String id, int qty) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update * from Item set Quantity=\'" + qty + "\' where ID=\'" + id + "\'";
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

    public static Item searchItem(String productID) {
        Item item = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select * from Item where item.ID=='" + productID + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.getString("ID").equals(productID)) {
                    String id = resultSet.getString(1);
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int quantity = resultSet.getInt(4);
                    int cost = resultSet.getInt(5);
                    String description = resultSet.getString(6);
                    item = new Item(id, type, name, quantity, cost, description);
                    connection.close();
                    return item;
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
