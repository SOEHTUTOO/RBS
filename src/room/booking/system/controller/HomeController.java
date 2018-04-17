/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton addVisitorBtn;
    @FXML
    private JFXButton exitBtn;
    @FXML
    private JFXButton addMemberBtn;
    @FXML
    private JFXButton roomBtn;
    @FXML
    private JFXButton visitorBtn;
    @FXML
    private JFXButton memberBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openVisitorForm(ActionEvent event) throws IOException {
        
        loadWindows("Visitor Form","/room/booking/system/view/addvisitor.fxml",new Image("/room/booking/system/icon/booking.png"));
        
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        
        Stage stage = (Stage) exitBtn.getScene().getWindow();
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("YOU ARE GOING TO EXIT. ARE YOU SURE?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    stage.close();
                }
        
    }

    @FXML
    private void openMemberForm(ActionEvent event) throws IOException {
        
        loadWindows("Member Form","/room/booking/system/view/addmember.fxml",new Image("/room/booking/system/icon/addmember.png"));
        
    }

    @FXML
    private void openRoomWindow(ActionEvent event) throws IOException {
        
        loadWindows("ROOMS","/room/booking/system/view/rooms.fxml",new Image("/room/booking/system/icon/rooms.png"));
        
    }

    @FXML
    private void openVisitorList(ActionEvent event) throws IOException {

        loadWindows("Visitors","/room/booking/system/view/visitors.fxml",new Image("/room/booking/system/icon/visitors.png"));
        
    }
    
    private void loadWindows (String title, String url, Image image) throws IOException{
    
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource(url));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    
    }

    @FXML
    private void openMemberWindow(ActionEvent event) throws IOException {
        
        loadWindows("Members","/room/booking/system/view/members.fxml",new Image("/room/booking/system/icon/members.png"));
        
    }
    
}
