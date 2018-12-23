//package controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class afterPageRecommendItemController  {
//
//    private ItemController itemController = new ItemController();
//    private String username;  //ใส่ไว้ก่อนถ้าจะดึงจาก db ตอนหลังค่อยลบ
//    @FXML
//    protected Button logoutButton;
//    @FXML
//    protected Label usernameIDLabel,itemLabel1,itemLabel2,itemLabel3,itemLabel4,itemLabel5,itemLabel6;
//    private ArrayList<Label> itemsLabel = new ArrayList<>();
//    private LoginController loginController = new LoginController();
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
//    @FXML
//    public void setUsername(String username){
//        this.username = username;               //ใส่ไว้ก่อนถ้าจะดึงจาก db ตอนหลังค่อยลบ
//        usernameIDLabel.setText(username);
//
//    }
//
//    public void logoutToHome(ActionEvent event) throws IOException {
//        loginController.setCheckLogin();
//        Button button = (Button) event.getSource();
//        Stage stage = (Stage) button.getScene().getWindow();
//        this.openHomeScreen(stage);
//    }
//
//    private void openHomeScreen(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/startRecommendItem.fxml"));
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
//
//        stage.show();
//    }
//
//    public void addButtonScene(ActionEvent event) throws IOException {
//        Button button = (Button) event.getSource();
//        Stage stage = (Stage) button.getScene().getWindow();
//        this.changeSceneAddButton(stage);
//    }
//
//    private void changeSceneAddButton(Stage stage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/additem.fxml"));
//        stage.setScene(new Scene(loader.load()));
//        ItemController itemController = loader.getController();
//        itemController.setUsername(this.username);
//        stage.show();
//    }
//}
