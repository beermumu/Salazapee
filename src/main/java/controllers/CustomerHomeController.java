package controllers;

import databases.CustomerItemDB;
import databases.InvoiceDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Invoice;
import models.Item;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

public class CustomerHomeController {
    private CustomerItemDB customerItemDB;

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
    InvoiceDB invoice;
    @FXML
    Button saveBtn;
    String accountID = "";
    @FXML
    private void initialize() {

        customerItemDB = new CustomerItemDB();
        columnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        columnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        ObservableList<Item> products = customerItemDB.loadDataOsv();
        Collections.sort(products, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (Integer.parseInt(o1.getId())<Integer.parseInt(o2.getId())) return -1;
                if (Integer.parseInt(o1.getId())>Integer.parseInt(o2.getId())) return 1;
                return 0;
            }
        });
        productTableView.setItems(products);
        buyQuan.setDisable(true);
        saveBtn.setDisable(true);
        purchaseBtn.setDisable(true);
    }
    public void setAccountID(String id){
        accountID = id;
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
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this item in cart?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            String id = cartTableView.getSelectionModel().getSelectedItem().getId();
            for (Item a : carts) {
                if (a.getId().equals(id)) {
                    carts.remove(a);
                }
            }
            update();
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"This item is deleted.");
            informationAlert.setTitle("Deleted");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
        }

    }
    String detail = "";
    Invoice model;
    @FXML
    public void purchaseBtn(ActionEvent event) {
        detail = "";
        int mess = 0;

        for (Item a : carts){
            Item b =  customerItemDB.searchItem(a.getId());
            customerItemDB.editItem(a.getId(),a.getType(),a.getName(),b.getQuantity() - a.getQuantity(),a.getCost(),a.getDescription());
            int price = a.getQuantity() * a.getCost();
            mess = mess + price;
            detail = detail + a.getName() + "(" + a.getQuantity() + ") ";
        }
        Date date = new Date();
        model = new Invoice(detail,date.toString(),accountID);
        updateMain();
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Thank you fot purchase\n Total cost is " + mess);
        informationAlert.setTitle("Purchase");
        informationAlert.setHeaderText("");
        informationAlert.showAndWait();
        invoice.saveInvoice(model.getDetails(),model.getDate(),model.getAccountID());
    }


    public void updateMain() {
        columnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        columnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        ObservableList<Item> products = customerItemDB.loadDataOsv();
        Collections.sort(products, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (Integer.parseInt(o1.getId())<Integer.parseInt(o2.getId())) return -1;
                if (Integer.parseInt(o1.getId())>Integer.parseInt(o2.getId())) return 1;
                return 0;
            }
        });
        productTableView.setItems(products);
        carts = FXCollections.observableArrayList();
        update();
    }
    @FXML
    public void clearCarts(ActionEvent event) {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to reset items in cart?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            carts = FXCollections.observableArrayList();
            purchaseBtn.setDisable(true);
            update();
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"This cart is reset.");
            informationAlert.setTitle("Reset");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
        }

    }

}
