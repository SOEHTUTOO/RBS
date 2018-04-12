/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import room.booking.system.model.Room;
import room.booking.system.model.RoomDAO;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class AddRoomController implements Initializable {

    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXTextArea roomIdField;
    @FXML
    private Spinner<Integer> slotSpinner;
    @FXML
    private JFXComboBox<String> buildingCombox;
    
    RoomDAO roomDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomDAO = new RoomDAO();
        
        SpinnerValueFactory integerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        slotSpinner.setValueFactory(integerFactory);
        
        buildingCombox.getItems().add("Austrilla");
        buildingCombox.getItems().add("Germany");
        buildingCombox.getItems().add("Japan");
        buildingCombox.getItems().add("G House");
        buildingCombox.getItems().add("G Dormantry");
        buildingCombox.getItems().add("Other Building");
        
        
    }    

    @FXML
    private void saveRoomDB(ActionEvent event) {
        
        String id = roomIdField.getText();
        int slot = slotSpinner.getValue();
        String building = buildingCombox.getValue();
        
        if(id.isEmpty() || building.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check Info");
            alert.setHeaderText(null);
            alert.setContentText("Please fill correct room detail.");
            alert.show();
            return;
        }
        
        try {
            
            roomDAO.addRoom(new Room(id,slot,building));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("Room Added.");
            alert.show();
        
        } catch (SQLException ex) {
          
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Something wrong!");
            alert.show();
        }
       
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
    }
    
    
}
