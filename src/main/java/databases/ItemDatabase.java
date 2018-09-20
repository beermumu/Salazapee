package databases;

import models.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDatabase {
    public void saveData(String type,String name,int quantity,int cost,String description){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Item.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null){
                String query = "insert into Data(type,name,quantity,cost,description) values(\'" + type
                        + "\',\'" + name + "\',\'" + quantity + "\',\'" + cost + "\',\'" + description + "')";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                connection.close();
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList loadData(){
        ArrayList<Item> items = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Item.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null){
                DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
                String query = "select * from Data";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int quantity = resultSet.getInt(4);
                    int cost = resultSet.getInt(5);
                    String description = resultSet.getString(6);
                    items.add(new Item(type,name,quantity,cost,description));
                }
                connection.close();
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}
