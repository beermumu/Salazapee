package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Item;

import java.io.IOException;

public class ItemDetailController {

    @FXML
    protected TextField itemDetailTextField = new TextField();

    private String detail;
    private Item item;

    @FXML
    Label priceLabel;
    @FXML
    protected TextField itemQuantity,quantity;
    @FXML
    protected Button addItemBtn, cancelBtn;

    public void setProductData(Item itemInput) {
        this.item = itemInput;
        System.out.println(itemInput.getId());
        System.out.println(itemInput.getName());
        itemQuantity.setText(itemInput.getName() + " " + itemInput.getDescription());
        quantity.setText("hi");
    }


    @FXML
    public void addQuantity(ActionEvent event){
        try {
            int qty = Integer.parseInt(itemQuantity.getText());
            int cost = qty * item.getCost();
            System.out.println(qty);
            System.out.println(cost);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemDetail-View.fxml"));
            stage.setScene(new Scene(loader.load()));
            CustomerHomeController customerHomeController = loader.getController();
            customerHomeController.addItemToCart(new Item(item.getId(), item.getType(), item.getName(),qty, cost, item.getDescription()));
            stage.show();
        } catch (Exception e) {
//            throw new IllegalArgumentException();
        }

    }

    @FXML
    public void clickCancelBtn(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerHome-View.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
