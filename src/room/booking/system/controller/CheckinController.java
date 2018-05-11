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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import room.booking.system.model.BookingDAO;
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
    BookingDAO bookingDAO;
    
    @FXML
    private JFXComboBox<String> memberCombo;
    @FXML
    private JFXTextField autoRoom;
    @FXML
    private FlowPane avaRoomsPane;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton closeBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        visitorDAO = new VisitorDAO();
        memberDAO = new MemberDAO();
        roomDAO = new RoomDAO();
        bookingDAO = new BookingDAO();
        
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
                if(id.isAvailable()){
                    createRoom(id);
                }
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

    @FXML
    private void saveCheckIn(ActionEvent event) throws SQLException {
        
        String passport = null;
        String roomID = autoRoom.getText();
        String memberID = null;
        
        ObservableList<Visitor> visList = visitorDAO.searchVisitors(autoSearch.getText());
        ObservableList<Member> memList = memberDAO.searchMembers(memberCombo.getValue());
        
        for(Visitor vis: visList){
            passport = vis.getPassport();
        }
        
        for(Member mem: memList){
            memberID = mem.getIdNo();
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("All detail correct?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    
                 try {
                bookingDAO.saveToRecordDB(roomID,passport,memberID);
            
                roomDAO.updateRoom(roomID);
                } catch (SQLException ex) {
                }   
                    
        }
        
        
        
        Alert susAlert = new Alert(Alert.AlertType.INFORMATION);
            susAlert.setTitle("SUCCESS");
            susAlert.setHeaderText(null);
            susAlert.setContentText("SUCCESS.");
            susAlert.show();
            
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }
    
}
