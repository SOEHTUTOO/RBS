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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    JFXButton roomBtn;
    
    @FXML
    private FlowPane gPane;
    @FXML
    private FlowPane ghPane;
    @FXML
    private FlowPane gdPane;
    @FXML
    private FlowPane jPane;
    @FXML
    private FlowPane oPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomDAO = new RoomDAO();
        
        refreshRoomList();
            
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
    
    private void refreshRoomList(){
    
        ObservableList<Room> roomList = null;
        
        try {
            roomList = roomDAO.getRoomList();
        } catch (SQLException ex) {
        
        }
   
        for(Room room:roomList){
            
            if(room.getBuilding().equals("Australia")){
                
                    createRoomBtn(room, aPane);
                
            }else if(room.getBuilding().equals("Germany")){
                
                createRoomBtn(room, gPane);
                
            }else if(room.getBuilding().equals("Japan")){
                
                createRoomBtn(room, jPane);
                
            }else if(room.getBuilding().equals("Guest House")){
                
                createRoomBtn(room, ghPane);
                
            }else if(room.getBuilding().equals("Girl Dormitory")){
                
                createRoomBtn(room, gdPane);
                
            }else{
                
                createRoomBtn(room, oPane);
            }
        }
    }
    
    private void createRoomBtn(Room room, Pane pane){
    
        JFXButton roomBtn = new JFXButton();
                roomBtn.setText(room.getId()+"\nVisitor Name");
                if(room.isAvailable()){
                    roomBtn.setStyle("-fx-background-color: #009688; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #004D40; -fx-font-size: 13; -fx-text-fill: #A7FFEB; -fx-font-weight: bold;");
                }else{
                    roomBtn.setStyle("-fx-background-color: #E91E63; -fx-pref-height: 50px; -fx-pref-width: 150px; -fx-border-width: 2px; -fx-border-color: #880E4F; -fx-font-size: 13; -fx-text-fill: #FCE4EC; -fx-font-weight: bold;");
                } 
                
                pane.getChildren().add(roomBtn);
                
                String btnText = roomBtn.getText();
                
                roomBtn.setOnAction((ActionEvent e)->{
                    
                    Label title = new Label(room.getId());
                    title.setPrefWidth(500);
                    title.setPrefHeight(40);
                    title.setStyle("-fx-font-weight:bold; -fx-font-size:20px; -fx-text-fill: white; -fx-background-color:#3F51B5; -fx-background-radius:20; -fx-alignment: CENTER;");
                    
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setPadding(new Insets(10,10,10,10));
                    hbox.getChildren().add(title);
                    
                    VBox vbox = new VBox();
                    vbox.getChildren().add(hbox);
                
                    Stage stage = new Stage();
        
                    Scene scene = new Scene(vbox,500, 450);
        
                    stage.getIcons().add(new Image("/room/booking/system/icon/visitors.png"));
                    stage.setScene(scene);
                    stage.setTitle("Info");
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.show();
                
                });
    
    }
    
    
}
    

