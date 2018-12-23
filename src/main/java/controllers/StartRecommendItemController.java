//package controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class StartRecommendItemController {
//
//    @FXML
//    protected Button signupButton,loginButton,cancelButton;
//    @FXML
//    protected Label usernameIDLabel,itemLabel1,itemLabel2,itemLabel3,itemLabel4,itemLabel5,itemLabel6;
//    private ArrayList<Label> itemsLabel = new ArrayList<>();
//    private ItemController itemController = new ItemController();
//    public void initialize(){
//        itemsLabel.add(itemLabel1);
//        itemsLabel.add(itemLabel2);
//        itemsLabel.add(itemLabel3);
//        itemsLabel.add(itemLabel4);
//        itemsLabel.add(itemLabel5);
//        itemsLabel.add(itemLabel6);
//        for (int i=0;i<itemController.itemArrayList.size();i++){
//            itemsLabel.get(i).setText(itemController.itemArrayList.get(i).getName() +" cost : "
//                    +itemController.itemArrayList.get(i).getCost());
//        }
//    }
//
//    public void changeToLoginPage(ActionEvent event) throws IOException {
//        Button button = (Button) event.getSource();
//        Stage stage = (Stage) button.getScene().getWindow();
//        this.loginPage(stage);
//    }
//
//    private void loginPage(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
//        stage.setScene(new Scene(loader.load()));
//        stage.show();
//    }
//
//
//    public void changeToSignUpPage(ActionEvent event) throws IOException {
//        Button button = (Button) event.getSource();
//        Stage stage = (Stage) button.getScene().getWindow();
//        this.signupPage(stage);
//    }
//
//    private void signupPage(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
//        stage.setScene(new Scene(loader.load()));
//        stage.show();
//    }
//
//    public void clickImageItem(ActionEvent event) throws IOException {
//        Button button = (Button) event.getSource();
//        Stage stage = (Stage) button.getScene().getWindow();
//        this.imagePage(stage);
//    }
//
//    private void imagePage(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showItem.fxml"));
//        stage.setScene(new Scene(loader.load()));
//        stage.show();
//    }
//}
