/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import room.booking.system.model.Room;
import room.booking.system.model.RoomDAO;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class RoomsController implements Initializable {

    @FXML
    private JFXButton addRoomBtn;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private FlowPane aPane;
    
    RoomDAO roomDAO;
    @FXML
    private FlowPane gPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomDAO = new RoomDAO();
        ObservableList<Room> roomList = null;
        JFXButton roomBtn;
        
        try {
            roomList = roomDAO.getRoomList();
        } catch (SQLException ex) {
            Logger.getLogger(RoomsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Room room:roomList){
            
            if(room.getBuilding().equals("Austrilla")){
                
                roomBtn = new JFXButton();
                roomBtn.setText(room.getId());
                if(room.isAvailable()){
                    roomBtn.setStyle("-fx-background-color: #009688; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #004D40; -fx-font-size: 15pt; -fx-text-fill: #A7FFEB");
                }else{
                    roomBtn.setStyle("-fx-background-color: #E91E63; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #880E4F; -fx-font-size: 15pt; -fx-text-fill: #FCE4EC");
                } 
                aPane.getChildren().add(roomBtn);
                
                String btnText = roomBtn.getText();
                
                roomBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        
                        
                        
                    }
                });
            }
            
            if(room.getBuilding().equals("Germany")){
                
                roomBtn = new JFXButton();
                roomBtn.setText(room.getId());
                if(room.isAvailable()){
                    roomBtn.setStyle("-fx-background-color: #009688; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #004D40; -fx-font-size: 15pt; -fx-text-fill: #A7FFEB");
                }else{
                    roomBtn.setStyle("-fx-background-color: #E91E63; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #880E4F; -fx-font-size: 15pt; -fx-text-fill: #FCE4EC");
                } 
                gPane.getChildren().add(roomBtn);
            }
            
            
            
        }

    }
           

    @FXML
    private void openRoomWindow(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/room/booking/system/view/addroom.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/room/booking/system/icon/rooms.png"));
        
        stage.setScene(scene);
        stage.setTitle("Add Room");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
    }

    @FXML
    private void closeRoomWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }
    
}
