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
import java.time.Instant;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class AddMemberController implements Initializable {

    @FXML
    private JFXButton cancelBtn;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void saveMember(ActionEvent event) {
        
        String name = nameField.getText();
        String id = idField.getText();
        String arcNo = arcField.getText();
        Date birth = java.sql.Date.valueOf(birthDatePicker.getValue());
        String gender = null;
        
        if(maleCheck.isSelected() && femaleCheck.isSelected()) {
        
           gender = "Please just choose one gender";
            
        }else if(maleCheck.isSelected()){
        
           gender = "Male";
            
        }else if(femaleCheck.isSelected()){
        
           gender = "Female";
            
        }else{
        
           gender = "Please choose your gender";
        }

        String mobile = mobileField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        
        System.out.println(name+id+arcNo+birth+gender+mobile+email+address);
        
    }
    
}
