package controllers;

import databases.ItemDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Accounts;
import models.Item;

import java.io.IOException;
import java.util.ArrayList;

public class ItemController {
    ItemDatabase itemDatabase =new ItemDatabase();
    ArrayList<Item> itemArrayList = itemDatabase.loadData();

    private String name;
    private int quantity;
    private int cost;
    private String description;

    @FXML
    protected TextField costTextField, nameTextField, desTextField, quantityTextField, typeTextField;
    @FXML
    protected Label costLabel;
    @FXML
    protected ImageView productImageView;
    @FXML
    protected Button saveBtn, cancelBtn, addPhotoBtn,nextButton;
    private LoginController loginController = new LoginController();
    private String link;

//    public ItemController(String name, int quantity, int cost, String description) {
//        this.name = name;
//        this.quantity = quantity;
//        this.cost = cost;
//        this.description = description;
//    }

    @FXML
    protected void handleAddPhotoBtn(ActionEvent event) {
    }

    @FXML
    protected void handleSaveBtn(ActionEvent event) {
        this.name = nameTextField.getText();
        this.description = desTextField.getText();
        this.cost = Integer.valueOf(costTextField.getText());
        this.quantity = Integer.valueOf(quantityTextField.getText());
    }


    public void testClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.testNext(stage);
    }


    private void testNext(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    public void addData(String type, String name, int quantity, int cost, String description){
        itemDatabase.saveData(type, name, quantity, cost, description);
    }

    public void showData(){
        for (int i = 0;i<itemArrayList.size();i++) {
            System.out.print(itemArrayList.get(i).getType());
            System.out.print(itemArrayList.get(i).getName());
            System.out.print(itemArrayList.get(i).getQuantity());
            System.out.print(itemArrayList.get(i).getCost());
            System.out.println(itemArrayList.get(i).getDescription());
        }
    }

    public void clickCancelImageInfo(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.goBackToHome(stage);
    }

    private void goBackToHome(Stage stage) throws IOException {
        if (loginController.getCheckLogin()){
            link = "/afterLoginRecommendItemHome.fxml";
        }else {
            link = "/startRecommendItem.fxml";
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(link));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void clickCancelAddMenu(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.goBackToLoginStart(stage);
    }

    private void goBackToLoginStart(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afterLoginRecommendItemHome.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    
}
