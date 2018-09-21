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
    private String type;
    private String username;

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

    @FXML
    protected void handleAddPhotoBtn(ActionEvent event) {
    }

    @FXML
    protected void handleSaveBtn(ActionEvent event) throws IOException {
        this.name = nameTextField.getText();
        this.description = desTextField.getPromptText();
        this.cost = Integer.valueOf(costTextField.getText());
        this.quantity = Integer.valueOf(quantityTextField.getText());
        this.type = typeTextField.getText();


//        ยังไม่ได้สั่งใช้นะเขียนไว้ก่อนเฉย ๆ
//        this.addData(this.type, this.name, this.quantity, this.cost,  this.description);

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afterLoginRecommendItemHome.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();

    }

    public void setUsername(String name) {
        this.username = name;
        System.out.println(this.username);
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
        afterPageRecommendItemController controller = loader.getController();
        controller.setUsername(username);
        stage.show();
    }
    
}
