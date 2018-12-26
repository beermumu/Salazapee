package controllers;

import databases.AdminAccountDB;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Accounts;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class AdminAllAccountController {

    private AdminAccountDB adminAccountDB;
    @FXML
    TableView<Accounts> accountsTableView ;
    @FXML protected TableColumn id,firstName,lastName,username,address,email,tel,details;

    @FXML
    Button addBtn,deleteBtn,backBtn;


    public void initialize (){
        adminAccountDB = new AdminAccountDB();
        id.setCellValueFactory(new PropertyValueFactory<Accounts,String>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Accounts,String>("firstname"));
        lastName.setCellValueFactory(new PropertyValueFactory<Accounts,String>("lastname"));
        username.setCellValueFactory(new PropertyValueFactory<Accounts,String>("username"));
        address.setCellValueFactory(new PropertyValueFactory<Accounts,String>("password"));
        address.setCellValueFactory(new PropertyValueFactory<Accounts,String>("address"));
        email.setCellValueFactory(new PropertyValueFactory<Accounts,String>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<Accounts,String>("tel"));
        details.setCellValueFactory(new PropertyValueFactory<Accounts,Integer>("details"));
        ObservableList<Accounts> accounts = adminAccountDB.loadAccount();
        Collections.sort(accounts, new Comparator<Accounts>() {
            @Override
            public int compare(Accounts o1, Accounts o2) {
                if (Integer.parseInt(o1.getId())<Integer.parseInt(o2.getId())) return -1;
                if (Integer.parseInt(o1.getId())>Integer.parseInt(o2.getId())) return 1;
                return 0;
            }
        });
        accountsTableView.setItems(accounts);
    }

    @FXML
    public void clickAddButton(ActionEvent event) throws Exception{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAccountPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    //delete button
    @FXML
    public void deleteAccount(ActionEvent event){
        String id = accountsTableView.getSelectionModel().getSelectedItem().getId();
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this account?", ButtonType.YES, ButtonType.NO);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.YES) {
            adminAccountDB.deleteAccount(id);
            accountsTableView.setItems(adminAccountDB.loadAccount());
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"This Accounts is deleted.");
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
