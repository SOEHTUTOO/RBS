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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openVisitorForm(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/room/booking/system/view/addvisitor.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        
        Stage stage = (Stage) exitBtn.getScene().getWindow();
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("YOU ARE GOING TO EXIT THIS APPLICATION. ARE YOU SURE?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    stage.close();
                }
        
    }
    
}
