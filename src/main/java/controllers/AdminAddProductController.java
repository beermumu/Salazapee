package controllers;

import databases.AdminItemDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminAddProductController {

    AdminItemDB adminItemDB;
    @FXML
    Button backBtn,addBtn;
    @FXML
    TextField id,name,quantity,cost,description;
    @FXML
    ComboBox type;
    ObservableList<String> types = FXCollections.observableArrayList("Food","Beverage","Snack");

    public void initialize(){
        type.setItems(types);
        type.getSelectionModel().selectFirst();
    }

    @FXML
    public void clickAddButton(ActionEvent event) throws Exception{
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this admin?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            if (id.getText().equals("")) {
                id.setStyle("-fx-border-color: red");
            } else {
                id.setStyle("-fx-border-color: black");
            }
            if (name.getText().equals("")) {
                name.setStyle("-fx-border-color: red");
            } else {
                name.setStyle("-fx-border-color: black");
            }
            if (quantity.getText().equals("")) {
                quantity.setStyle("-fx-border-color: red");
            } else {
                quantity.setStyle("-fx-border-color: black");
            }
            if (cost.getText().equals("")) {
                cost.setStyle("-fx-border-color: red");
            } else {
                cost.setStyle("-fx-border-color: black");
            }
            if (description.getText().equals("")) {
                description.setStyle("-fx-border-color: red");
            } else {
                description.setStyle("-fx-border-color: black");
            }

            if (checkID(id.getText())) {
                if (isNumeric(quantity.getText())){
                    if (isNumeric(cost.getText())){
                        String types = type.getValue().toString();
                        int quan = Integer.parseInt(quantity.getText());
                        int costs = Integer.parseInt(cost.getText());
                        adminItemDB.saveItem(id.getText(),types,name.getText(),quan,costs,description.getText());
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "This Item is saved.");
                        informationAlert.setTitle("Saved");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                        clickBackButton(event);
                    }else {
                        cost.setStyle("-fx-border-color: red");
                    }
                }else {
                    quantity.setStyle("-fx-border-color: red");
                }
            }else {
                id.setStyle("-fx-border-color: red");
            }
        } else {
            String errorMessage = "Could not save this item !!!";
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, errorMessage);
            errorAlert.setTitle("ERROR!");
            errorAlert.setHeaderText("");
            errorAlert.showAndWait();
        }
    }

    public boolean checkID(String id){
        if (adminItemDB.getItemID().contains(id)){
            return false;
        }return true;
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
    public void clickBackButton(ActionEvent event) throws Exception{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminProductPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
