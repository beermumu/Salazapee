package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Accounts;

import java.sql.*;
import java.util.ArrayList;

public class AdminAccountDB {

    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static void saveAdmin(String id, String firstname, String lastname, String username, String password, String address, String email, String tel) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                int status = 1;
                String query = "insert into Account (ID, FirstName, LastName, Username, Password, Address, Email, Tel, Status) values " +
                        "('" + id + "' , '" + firstname + "' , '" + lastname  + "' , '" + username + "' , '" + password + "' , '"
                        + address + "' , '" + email + "' , '" + tel + "' , '"  + status + "')";
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
    public static ObservableList loadAccount() {
        ObservableList<Accounts> accounts = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Account";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String firstname = resultSet.getString("FirstName");
                    String lastname = resultSet.getString("LastName");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    String address = resultSet.getString("Address");
                    String email = resultSet.getString("Email");
                    String tel = resultSet.getString("Tel");
                    int status = resultSet.getInt("Status");
                    accounts.add(new Accounts(id,firstname,lastname,username,password,address,email,tel,status));
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

    public static void deleteAccount(String id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Account where ID == '" + id + "'";
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

    public static ArrayList getAccountID(){
        ArrayList<String> accounts = new ArrayList<>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Account";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    accounts.add(id);
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
