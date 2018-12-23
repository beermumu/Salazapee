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
        boolean isPass = true;
        if (optional.get() == ButtonType.YES) {
            if (id.getText().equals("")) {
                id.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                id.setStyle("-fx-border-color: black");
            }
            if (firstname.getText().equals("")) {
                firstname.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                firstname.setStyle("-fx-border-color: black");
            }
            if (lastname.getText().equals("")) {
                lastname.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                lastname.setStyle("-fx-border-color: black");
            }
            if (username.getText().equals("")) {
                username.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                username.setStyle("-fx-border-color: black");
            }
            if (address.getText().equals("")) {
                address.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                address.setStyle("-fx-border-color: black");
            }
            if (password.getText().equals("")) {
                password.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                lastname.setStyle("-fx-border-color: black");
            }
            if (email.getText().equals("")) {
                email.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                email.setStyle("-fx-border-color: black");
            }
            if (tel.getText().equals("") || isNumeric(tel.getText())) {
                tel.setStyle("-fx-border-color: blue");
                isPass =false;
            } else {
                tel.setStyle("-fx-border-color: black");
            }
            if (checkID(id.getText()) && isPass) {
                accountDB.saveAdmin(id.getText(), firstname.getText(), lastname.getText(), username.getText(), password.getText(), address.getText(), email.getText(), tel.getText());
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "This Account is saved.");
                informationAlert.setTitle("Saved");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                clickAddButton(event);
            }else {
                id.setStyle("-fx-border-color: blue");
            }
        } else {
            String errorMessage = "Could not save this subject !!!";
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, errorMessage);
            errorAlert.setTitle("ERROR!");
            errorAlert.setHeaderText("");
            errorAlert.showAndWait();
        }
    }

    public boolean checkID(String id){
        if (accountDB.getAccountID().contains(id)){
            return false;
        }return true;
    }
    public  boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return true;
        }
        return false;
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
