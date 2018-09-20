package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartRecommendItemController {

    @FXML
    protected Button signupButton,loginButton,cancelButton;

    public void changeToLoginPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.loginPage(stage);
    }

    private void loginPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    public void changeToSignUpPage(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.signupPage(stage);
    }

    private void signupPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


}
