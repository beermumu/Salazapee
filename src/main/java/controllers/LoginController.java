package controllers;

import databases.AccountDB;
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
    private AccountDB accountsDB = new AccountDB();
    @FXML
    private Text warningText;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    private ObservableList<Accounts> accounts;



    public void initialize() {
        accounts = accountsDB.loadAccounts();
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

    public void cancelLogin(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.backToStartHome(stage);
    }

    private void backToStartHome(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/startRecommendItem.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void loginOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.checkUsernameAndPassword(stage);
    }

    private void checkUsernameAndPassword(Stage stage) throws IOException {
        boolean loginSuccess = false;
        for (Accounts i : accounts) {
            if (i.getUsername().equals(userName.getText()) && i.getPassword().equals(userPassword.getText())) {
                String name = userName.getText();
                this.loginPassToProgram(stage,name);
                loginSuccess = true;
                break;
            }
        }
        if (!loginSuccess) {
            warningText.setText("Username or Password is incorrect");
        }
    }

    // error cannot sent name to afterPage...Controller
    private void loginPassToProgram(Stage stage,String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afterLoginRecommendItemHome.fxml"));
        stage.setScene(new Scene(loader.load()));

        afterPageRecommendItemController controller = loader.getController();
        controller.setUsername(name);
        stage.show();
        warningText.setText("");
    }
}
