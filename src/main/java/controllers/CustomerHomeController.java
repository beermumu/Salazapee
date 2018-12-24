package controllers;

import databases.CustomerItemDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Cart;
import models.Item;

import java.io.IOException;
import java.util.Optional;

public class CustomerHomeController {
    private CustomerItemDB customerItemDB;
    private Cart cart;

    @FXML
    TableView<Item> productTableView, cartTableView;
    @FXML
    protected TableColumn columnID, columnType, columnName, columnQuantity, columnCost, columnDescription,
            cartColumnID, cartColumnType, cartColumnName, cartColumnQuantity, cartColumnCost, cartColumnDescription;
    @FXML
    private ComboBox typeCombobox;
    @FXML
    private Button deleteProductBtn, clearCartBtn, purchaseBtn, searchProductBtn, resetFillterBtn;
    @FXML
    TextField buyQuan;
    ObservableList<Item> carts = FXCollections.observableArrayList();
    @FXML
    Button saveBtn;

    @FXML
    private void initialize() {
        cart = new Cart();
        customerItemDB = new CustomerItemDB();
        columnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        columnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        productTableView.setItems(customerItemDB.loadDataOsv());
        buyQuan.setDisable(true);
        saveBtn.setDisable(true);
        purchaseBtn.setDisable(true);
    }

    @FXML
    public void clickOnProduct(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            buyQuan.setDisable(false);
            saveBtn.setDisable(false);
        }
    }

    @FXML
    public void clickSaveButton(ActionEvent event) throws Exception {
        int quanT = productTableView.getSelectionModel().getSelectedItem().getQuantity();
        if (buyQuan.getText().equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Fill the correct input.");
            errorAlert.setTitle("ERROR!");
            errorAlert.setHeaderText("");
            errorAlert.showAndWait();
        }

        int quan = Integer.parseInt(buyQuan.getText());
        if (quan > quanT) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR,"You require is more than stock.");
            errorAlert.setTitle("ERROR!");
            errorAlert.setHeaderText("");
            errorAlert.showAndWait();
        } else {
            if (buyQuan.getText().equals("0")) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Please enter quantity more than 0.");
                errorAlert.setTitle("ERROR!");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
                buyQuan.setText("");
            }else {
                String id = productTableView.getSelectionModel().getSelectedItem().getId();
                int oldQuan = 0;
                for (Item b : carts) {
                    if (b.getId().equals(id)) {
                        oldQuan = oldQuan + b.getQuantity();
                    }
                }
                oldQuan = oldQuan + quan;
                if (oldQuan > quanT) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You require is more than stock.");
                    errorAlert.setTitle("ERROR!");
                    errorAlert.setHeaderText("");
                    errorAlert.showAndWait();
                } else {
                    Item a = customerItemDB.searchItem(id, quan);
                    carts.add(a);
                    update();
                }
                buyQuan.setText("");
            }
        }
        purchaseBtn.setDisable(false);
    }


    public void update() {
        cartColumnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        cartColumnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        cartColumnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        cartColumnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        cartColumnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        cartColumnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        cartTableView.setItems(carts);
    }

    @FXML
    public void deleteAccount(ActionEvent event) {
        String id = cartTableView.getSelectionModel().getSelectedItem().getId();
        for (Item a : carts) {
            if (a.getId().equals(id)) {
                carts.remove(a);
            }
        }
        update();
    }

    @FXML
    public void purchaseBtn(ActionEvent event) {
        int mess = 0;
        for (Item a : carts){
            Item b =  customerItemDB.searchItem(a.getId());
            customerItemDB.editItem(a.getId(),a.getType(),a.getName(),b.getQuantity() - a.getQuantity(),a.getCost(),a.getDescription());
            int price = a.getQuantity() * a.getCost();
            mess = mess + price;
        }
        updateMain();
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Thank you fot purchase\n Total cost is " + mess);
        informationAlert.setTitle("Purchase");
        informationAlert.setHeaderText("");
        informationAlert.showAndWait();
    }

    public void updateMain() {
        columnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        columnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        productTableView.setItems(customerItemDB.loadDataOsv());
        carts = FXCollections.observableArrayList();
        update();
    }
    @FXML
    public void clearCarts(ActionEvent event) {
        carts = FXCollections.observableArrayList();
        purchaseBtn.setDisable(true);
        update();
    }


    public void addItemToCart(Item item) {
        cart.addItem(item);
        cartColumnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        cartColumnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        cartColumnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        cartColumnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        cartColumnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        cartColumnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        cartTableView.setItems((cart.getCart()));
    }
}
