package controllers;

import databases.AdminItemDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Item;


public class AdminAllProductPage {

    private AdminItemDB adminItemDB;
    @FXML
    TableView<Item> productTableView ;
    @FXML protected TableColumn id,type,name,quantity,cost,description;
    @FXML
    Button addBtn,editBtn,deleteBtn;


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
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editProductPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    //delete button
}
