package databases;

import models.Invoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoiceDB {

    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";
    private static int id = 0;
    public static void saveInvoice(String detail, String date , String account) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {

                String query = "insert into Invoice (ID, Details,Date,AccountID) values " +
                        "('" + id + "' , '" +  detail + "' , '" + date + "' , '" +  account + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
                id++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }


}
