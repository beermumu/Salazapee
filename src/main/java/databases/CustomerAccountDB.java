package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Accounts;

import java.sql.*;

public class CustomerAccountDB {
    public static ObservableList loadAccounts() {
        ObservableList<Accounts> accounts = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Account";
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
                    int status = resultSet.getInt(9);
                    accounts.add(new Accounts(id, firstName, lastName, username, password, address, email, tel, status));
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
        Connection connection = null;
        PreparedStatement p = null;
        String query = "insert into Account (id, firstName, lastName, username, password, address , email , tel , status) values (\'" + id + "\', \'" + firstname + "\' , \'" + lastname + "\' , \'" + username + "\' , \'" + password + "\',\'" + address + "\',\'" + email + "\',\'" + tel + "\',\'" + 0 + "\')";

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                p = connection.prepareStatement(query);
                p.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                p.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void deleteAccountsCustomerDB(int id) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            String dbURL = "jdbc:sqlite:Database.db";
//            Connection connection = DriverManager.getConnection(dbURL);
//            if (connection != null) {
//                String query = "Delete from Account where id == \'" + id + "\'";
//                PreparedStatement p = connection.prepareStatement(query);
//                p.executeUpdate();
//                connection.close();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void editAccountsCustomerDB(int id, String firstname, String lastname, String username, String password, String address, String email, String tel) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Account set id=\'" + id + "\' ,firstname=\'" + firstname +
                        "\' ,lastname=\'" + lastname + "\' ,username=\'" + username + "\' ,password=\'" + password + "\' ,address=\'" + address + "\' ,email=\'" + email + "\' ,tel=\'" + tel + "\',status=\'" + 0 + "\' where ID == \'" + id + "\'";
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

    public static int getCreateAccountsID() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Database.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select max(id) from Account";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int minID = resultSet.getInt(1);
                connection.close();
                return minID + 1;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
