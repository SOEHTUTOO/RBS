/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
}
