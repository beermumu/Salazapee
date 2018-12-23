package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javax.swing.text.TabableView;

public class paymentController {

    @FXML
    private TableView purchaseTableView;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Button purchaseBtn, cancelBtn;
}
