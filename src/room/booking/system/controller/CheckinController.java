/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;
import room.booking.system.model.Member;
import room.booking.system.model.MemberDAO;
import room.booking.system.model.Room;
import room.booking.system.model.RoomDAO;
import room.booking.system.model.Visitor;
import room.booking.system.model.VisitorDAO;

/**
 * FXML Controller class
 *
 * @author spell
 */
public class CheckinController implements Initializable {

    @FXML
    private JFXTextField autoSearch;
    

    VisitorDAO visitorDAO;
    MemberDAO memberDAO;
    RoomDAO roomDAO;
    
    @FXML
    private JFXComboBox<String> memberCombo;
    @FXML
    private JFXTextField autoRoom;
    @FXML
    private FlowPane avaRoomsPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        visitorDAO = new VisitorDAO();
        memberDAO = new MemberDAO();
        roomDAO = new RoomDAO();
        
        memberCombo.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
        
        Collection<String> visName=  new ArrayList<>();
        Collection<String> roomID=  new ArrayList<>();
        
        try {
            ObservableList<Visitor> visList = visitorDAO.getVisitorList();
            for(Visitor vis:visList){
                visName.add(vis.getName());
            }
            TextFields.bindAutoCompletion(autoSearch, visName);
            
        } catch (SQLException ex) {
        }
        
        try {
            ObservableList<Room> idList = roomDAO.getRoomList();
            for(Room id:idList){
                roomID.add(id.getId());
                createRoom(id);
            }
            TextFields.bindAutoCompletion(autoRoom, roomID);
        } catch (SQLException ex) {
        }
        
        try {
            ObservableList<Member> memList = memberDAO.getMemberList();
            for(Member mem:memList){
                memberCombo.getItems().add(mem.getName());
            }
        } catch (SQLException ex) {
        }
        
        
        
        
        
        
    } 
    
    private void createRoom(Room room){
    
        JFXButton roomBtn = new JFXButton();
                roomBtn.setText(room.getId());
                
                if(room.isAvailable()){
                    roomBtn.setStyle("-fx-background-color: #009688; -fx-pref-height: 35px; -fx-pref-width: 90px; -fx-border-width: 1px; -fx-border-color: #004D40; -fx-font-size: 12; -fx-text-fill: #A7FFEB; -fx-font-weight: bold;");
                }
                
                avaRoomsPane.getChildren().add(roomBtn);
                
                String btnText = roomBtn.getText();
                
                roomBtn.setOnAction(e->{
                
                    autoRoom.setText(room.getId());
                
                });
    
    }
    
}
