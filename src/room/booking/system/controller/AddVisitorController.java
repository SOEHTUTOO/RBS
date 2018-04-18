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
import room.booking.system.model.Visitor;
import room.booking.system.model.VisitorDAO;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class AddVisitorController implements Initializable {

    @FXML
    private JFXTextField nameField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private JFXTextField passportField;
    @FXML
    private JFXTextField nationField;
    @FXML
    private JFXTextField organizeField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXCheckBox maleCheck;
    @FXML
    private JFXCheckBox femaleCheck;
    @FXML
    private DatePicker arrivalDatePicker;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private JFXTextArea addressField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXTextField visaField;

    VisitorDAO visitorDAO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        visitorDAO = new VisitorDAO();
        
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void saveVisitorToDB(ActionEvent event) {
        
        String name = nameField.getText();
        
        Date birth = null;
        if(birthDatePicker.getValue()!=null){
        birth = java.sql.Date.valueOf(birthDatePicker.getValue());}
        
        Date arrival = null;
        if(arrivalDatePicker.getValue()!=null){
        arrival = java.sql.Date.valueOf(arrivalDatePicker.getValue());}
        
        Date departure = null;
        if(departureDatePicker.getValue()!=null){
        departure = java.sql.Date.valueOf(birthDatePicker.getValue());}
        
        String passport = passportField.getText();
        String visa = visaField.getText();
        String nation = nationField.getText();
        String organization = organizeField.getText();
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
        
        
        if(name.isEmpty() || birth==null || arrival==null || departure==null || passport.isEmpty() || visa.isEmpty() || nation.isEmpty() || organization.isEmpty() || mobile.isEmpty() || email.isEmpty() || gender==null || address.isEmpty()){
                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check The Info");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.show();
            return;
        }
        
        try {
            visitorDAO.addVisitorToDB(new Visitor(name,birth,arrival,departure,passport,visa,nation,organization,mobile,email,gender,address));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("SUCCESS.");
            alert.show();
            
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
            
        } catch (SQLException ex) {
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAIL");
            alert.setHeaderText(null);
            alert.setContentText("FAIL.");
            alert.show();
            
        }
        
        
    }
    
}
