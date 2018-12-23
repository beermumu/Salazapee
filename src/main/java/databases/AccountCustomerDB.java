package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Accounts;

import java.sql.*;

public class AccountCustomerDB {
    public static ObservableList loadAccounts() {
        ObservableList<Accounts> accounts = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Accounts";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String firstName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    String username = resultSet.getString(4);
                    String password = resultSet.getString(5);
                    String address = resultSet.getString(6);
                    String email = resultSet.getString(7);
                    String tel = resultSet.getString(8);
                    accounts.add(new Accounts(id, firstName, lastName, username, password, address, email, tel,0));
                }
                //close connection
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }

    public static void saveAccountsCustomerDB(int id, String firstname, String lastname, String username, String password, String address, String email, String tel) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Accounts (id, firstName, lastName, username, password, address , email , tel) values (\'" + id + "\', \'" + firstname + "\' , \'" + lastname + "\' , \'" + username + "\' , \'" + password + "\',\'" + address + "\',\'" + email + "\',\'" + tel + "\',\'"+0+"\')";
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

    public static void deleteAccountsCustomerDB(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Accounts where id == \'" + id + "\'";
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

    public static void editAccountsCustomerDB(int id, String firstname, String lastname, String username, String password, String address, String email, String tel) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Accounts set id=\'" + id + "\' ,firstname=\'" + firstname +
                        "\' ,lastname=\'" + lastname + "\' ,username=\'" + username + "\' ,password=\'" + password + "\' ,address=\'" + address + "\' ,email=\'" + email + "\' ,tel=\'" + tel + "\' where ID == \'" + id + "\',\'"+0+"\'";
                System.out.println(query);
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
