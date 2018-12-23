package controllers;

import databases.AdminAccountDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminAddAdminController {

    AdminAccountDB accountDB;
    @FXML
    Button backBtn, addBtn;
    @FXML
    TextField id, firstname, lastname, username, address, password, email, tel;

    @FXML
    public void checkFill(ActionEvent event) throws Exception {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this admin?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            if (id.getText().equals("")) {
                id.setStyle("-fx-border-color: red");
            } else {
                id.setStyle("-fx-border-color: black");
            }
            if (firstname.getText().equals("")) {
                firstname.setStyle("-fx-border-color: red");
            } else {
                firstname.setStyle("-fx-border-color: black");
            }
            if (lastname.getText().equals("")) {
                lastname.setStyle("-fx-border-color: red");
            } else {
                lastname.setStyle("-fx-border-color: black");
            }
            if (username.getText().equals("")) {
                username.setStyle("-fx-border-color: red");
            } else {
                username.setStyle("-fx-border-color: black");
            }
            if (address.getText().equals("")) {
                address.setStyle("-fx-border-color: red");
            } else {
                address.setStyle("-fx-border-color: black");
            }
            if (password.getText().equals("")) {
                password.setStyle("-fx-border-color: red");
            } else {
                lastname.setStyle("-fx-border-color: black");
            }
            if (email.getText().equals("")) {
                email.setStyle("-fx-border-color: red");
            } else {
                email.setStyle("-fx-border-color: black");
            }
            if (tel.getText().equals("") && isNumeric(tel.getText())) {
                tel.setStyle("-fx-border-color: red");
            } else {
                tel.setStyle("-fx-border-color: black");
            }
            accountDB.saveAdmin(id.getText(),firstname.getText(),lastname.getText(),username.getText(),password.getText(),address.getText(),email.getText(),tel.getText());
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "This Account is saved.");
            informationAlert.setTitle("Saved");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
            clickAddButton(event);
        } else {
            String errorMessage = "Could not save this subject !!! \n Please enter correct information";
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, errorMessage);
            errorAlert.setTitle("ERROR!");
            errorAlert.setHeaderText("");
            errorAlert.showAndWait();
        }
    }

    public  boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    public void clickAddButton(ActionEvent event) throws Exception {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminAccountPage.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.show();
    }

    @FXML
    public void clickBackButton(ActionEvent event) throws Exception {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminAccountPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


}
