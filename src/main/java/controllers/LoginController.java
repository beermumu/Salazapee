package controllers;

import databases.CustomerAccountDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;

public class LoginController {
    private CustomerAccountDB customerAccountDB = new CustomerAccountDB();
    @FXML
    private Text warningText;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    private ObservableList<Accounts> accounts;
    private boolean loginSuccess;
    private boolean checklogin = false;

    public Boolean getCheckLogin() {
        return checklogin;
    }

    public void setCheckLogin() {
        checklogin = false;
    }

    public void initialize() {
        accounts = customerAccountDB.loadAccounts();
        userPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    PasswordField passwordField = (PasswordField) event.getSource();
                    Stage stage = (Stage) passwordField.getScene().getWindow();
                    try {
                        checkUsernameAndPassword(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void register(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    public void loginOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.checkUsernameAndPassword(stage);
    }

    private void checkUsernameAndPassword(Stage stage) throws IOException {
        loginSuccess = false;
        for (Accounts i : accounts) {
            if (i.getUsername().equals(userName.getText()) && i.getPassword().equals(userPassword.getText())) {
                String name = userName.getText();
                if (i.getStatus() == 0) {
                    this.loginPassToCustomerPage(stage, name);
                } else if (i.getStatus() == 1) {
                    this.loginPassToAdminPage(stage, name);
                }
                loginSuccess = true;
                checklogin = true;
                break;
            }
        }
        if (!loginSuccess) {
            warningText.setText("Username or Password is incorrect");
        }
    }

    // error cannot sent name to afterPage...Controller
    private void loginPassToCustomerPage(Stage stage, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerHome-View.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        warningText.setText("");
    }

    private void loginPassToAdminPage(Stage stage, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminStartPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        warningText.setText("");
    }
}
