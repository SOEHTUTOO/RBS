/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import room.booking.system.model.Member;
import room.booking.system.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class AddMemberController implements Initializable {

    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField arcField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private JFXCheckBox maleCheck;
    @FXML
    private JFXCheckBox femaleCheck;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextArea addressField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton closeBtn;
    
    MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void saveMemberToDB(ActionEvent event) {
        
        String name = nameField.getText();
        
        Date birth = null;
        if(birthDatePicker.getValue()!=null){
        birth = java.sql.Date.valueOf(birthDatePicker.getValue());}
        
        String id = idField.getText();
        String arcNo = arcField.getText();
        String mobile = mobileField.getText();
        String email = emailField.getText();
        
        String gender = null;
        
        if(maleCheck.isSelected() && femaleCheck.isSelected()) {
        
           gender = null;
            
        }else if(maleCheck.isSelected()){
        
           gender = "Male";
            
        }else if(femaleCheck.isSelected()){
        
           gender = "Female";
            
        }else{
        
           gender = null;
        }
        
        String address = addressField.getText();
        
        if(name.isEmpty() || id.isEmpty() || arcNo.isEmpty() || birth==null || gender==null || mobile.isEmpty() || email.isEmpty() || address.isEmpty()){
            
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check The Info");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.show();
            return;
            
        }
        
        try {
            memberDAO.addMemberToDB(new Member(name,birth,id,arcNo,mobile,email,gender,address));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("SUCCESS.");
            alert.show();
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAIL");
            alert.setHeaderText(null);
            alert.setContentText("FAIL.");
            alert.show();
            
        }
        
    }
    
}
