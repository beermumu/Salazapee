package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class CustomerHomeController {

    @FXML
    private TableView productTableView, cartTableView;
    @FXML
    private ComboBox typeCombobox;
    @FXML
    private Button deleteProductBtn, clearCartBtn, purchaseBtn, searchProductBtn, resetFillterBtn;

}
