package controllers;

import databases.CustomerItemDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Cart;
import models.Item;

import java.io.IOException;

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

        cartColumnID.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
        cartColumnType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));
        cartColumnName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        cartColumnQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        cartColumnCost.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        cartColumnDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        cartTableView.setItems((cart.getCart()));

//        cartTableView.setOnAction( e -> changeCourse());
//        public void changeCourse(){
//            try {
//                courseChange.remove(courseComboBox.getValue());
//                mainCourse = FXCollections.observableArrayList(courseChange);
//                courseChange.addAll(course);
//                courseComboBox2.setItems(mainCourse);
//            }catch (NullPointerException e){}
//        }

    }

    @FXML
    public void clickOnProduct(MouseEvent event) throws IOException {
        Item item = (Item) productTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
//            System.out.println(productTableView.getSelectionModel().getSelectedItem().getId());
//            System.out.println(productTableView.getSelectionModel().getSelectedItem().getDescription());
//            System.out.println(productTableView.getSelectionModel().getSelectedItem().getName());
//            System.out.println(productTableView.getSelectionModel().getSelectedItem().getQuantity());
//            System.out.println(productTableView.getSelectionModel().getSelectedItem().getCost());

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemDetail-View.fxml"));
            stage.setScene(new Scene(loader.load()));
            ItemDetailController itemDetailController = loader.getController();
            itemDetailController.setProductData(customerItemDB.searchItem(productTableView.getSelectionModel().getSelectedItem().getId()));
            stage.show();
        }
    }

    public void addItemToCart(Item item) {
        cart.addItem(item);
    }
}
