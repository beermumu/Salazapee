package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class afterPageRecommendItemController  {

    @FXML
    protected Button logoutButton;
    @FXML
    protected Label usernameIDLabel;

    @FXML
    public void setUsername(String username){
        usernameIDLabel.setText(username);
    }


}
