package controllers;

import databases.AdminItemDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Item;

import java.util.Optional;


public class AdminAllProductPageController {

    private AdminItemDB adminItemDB;
    @FXML
    TableView<Item> productTableView ;
    @FXML protected TableColumn id,type,name,quantity,cost,description;
    @FXML
    Button addBtn,editBtn,deleteBtn,backBtn;


    public void initialize (){
        adminItemDB = new AdminItemDB();
        id.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        type.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
        name.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
        cost.setCellValueFactory(new PropertyValueFactory<Item,Integer>("cost"));
        description.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        productTableView.setItems(adminItemDB.loadItem());
    }

    @FXML
    public void clickAddButton(ActionEvent event) throws Exception{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addProductPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    public void clickEditButton(ActionEvent event) throws Exception{
        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/editProductPage.fxml"));
            stage.setScene(new Scene(loader.load()));
            AdminEditProductController edit = loader.getController();
            edit.setEditAccounts(productTableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

    //delete button
    @FXML
    public void deleteAccount(ActionEvent event){
//        String id = accountsTableView.getSelectionModel().getSelectedItem().getId();
        String id = productTableView.getSelectionModel().getSelectedItem().getId();
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this item?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            adminItemDB.deleteItem(id);
            productTableView.setItems(adminItemDB.loadItem());
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"This Item is deleted.");
            informationAlert.setTitle("Deleted");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
        }
    }

    @FXML
    public void clickBackButton(ActionEvent event) throws Exception{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminStartPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
