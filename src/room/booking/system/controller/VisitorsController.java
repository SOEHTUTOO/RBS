/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import room.booking.system.model.Visitor;
import room.booking.system.model.VisitorDAO;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class VisitorsController implements Initializable {

    @FXML
    private TableColumn<Visitor, String> nameCol;
    @FXML
    private TableColumn<Visitor, String> passportCol;
    @FXML
    private TableColumn<Visitor, String> nationCol;
    @FXML
    private TableColumn<Visitor, String> mobileCol;
    @FXML
    private TableColumn<Visitor, String> emailCol;

    VisitorDAO visitorDAO;
    
    @FXML
    private TableView<Visitor> visitorTable;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton clearBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        visitorDAO = new VisitorDAO();
        
        visitorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passport"));
        nationCol.setCellValueFactory(new PropertyValueFactory<>("nation"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        ObservableList<Visitor> visitorList = null;
        try {
            visitorList = visitorDAO.getVisitorList();
        } catch (SQLException ex) {
        }
        
        visitorTable.getItems().addAll(visitorList);
        
    }    

    @FXML
    private void searchWithField(ActionEvent event) throws SQLException {
        
        String searchText = searchField.getText();
        
        ObservableList<Visitor> visitorList = visitorDAO.searchVisitors(searchText);
        
        if(visitorList.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Data Found!");
            alert.setHeaderText(null);
            alert.show();
            
            return;
            
        }
        
        for ( int i = 0; i<visitorTable.getItems().size(); i++) {
            visitorTable.getItems().clear();
        }
        
        visitorTable.getItems().addAll(visitorList);
        
    }

    @FXML
    private void searchWithBtn(ActionEvent event) throws SQLException {
        
        String searchText = searchField.getText();
        
        ObservableList<Visitor> visitorList = visitorDAO.searchVisitors(searchText);
        
        if(visitorList.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Data Found!");
            alert.setHeaderText(null);
            alert.show();
            
            return;
            
        }
        
        for ( int i = 0; i<visitorTable.getItems().size(); i++) {
            visitorTable.getItems().clear();
        }
        
        visitorTable.getItems().addAll(visitorList);
        
    }

    @FXML
    private void clearTable(ActionEvent event) {
        
        for ( int i = 0; i<visitorTable.getItems().size(); i++) {
            visitorTable.getItems().clear();
        }
        
        ObservableList<Visitor> visitorList = null;
        try {
            visitorList = visitorDAO.getVisitorList();
        } catch (SQLException ex) {
        }
        
        visitorTable.getItems().addAll(visitorList);
        
    }
    
}
