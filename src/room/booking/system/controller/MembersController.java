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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import room.booking.system.model.Member;
import room.booking.system.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author soehtutoo
 */
public class MembersController implements Initializable {

    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> arcCol;
    @FXML
    private TableColumn<Member, String> mobileCol;
    
    MemberDAO memberDAO;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton clearBtn;
    @FXML
    private TableColumn<Integer, Integer> noCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
        memberTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        arcCol.setCellValueFactory(new PropertyValueFactory<>("arcNo"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        
        noCol.setSortable(false);
        noCol.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(memberTable.getItems().indexOf(column.getValue())+1));
        
        ObservableList<Member> memberList = null;
        
        try {
            memberList = memberDAO.getMemberList();
        } catch (SQLException ex) {
            Logger.getLogger(MembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        memberTable.getItems().addAll(memberList);
        
    }    

    @FXML
    private void searchWithField(ActionEvent event) throws SQLException {
        
        String searchText = searchField.getText();
        
        ObservableList<Member> memberList = memberDAO.searchMembers(searchText);
        
        if(memberList.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Data Found!");
            alert.setHeaderText(null);
            alert.show();
            
            return;
            
        }
        
        for ( int i = 0; i<memberTable.getItems().size(); i++) {
            memberTable.getItems().clear();
        }
        
        memberTable.getItems().addAll(memberList);
        
        
    }

    @FXML
    private void searchWithBtn(ActionEvent event) throws SQLException {
        
        String searchText = searchField.getText();
        
        ObservableList<Member> memberList = memberDAO.searchMembers(searchText);
        
        if(memberList.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Data Found!");
            alert.setHeaderText(null);
            alert.show();
            
            return;
            
        }
        
        for ( int i = 0; i<memberTable.getItems().size(); i++) {
            memberTable.getItems().clear();
        }
        
        memberTable.getItems().addAll(memberList);
        
    }

    @FXML
    private void clearTable(ActionEvent event) {
        
        for ( int i = 0; i<memberTable.getItems().size(); i++) {
            memberTable.getItems().clear();
        }
        
        ObservableList<Member> memberList = null;
        
        try {
            memberList = memberDAO.getMemberList();
        } catch (SQLException ex) {
            Logger.getLogger(MembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        memberTable.getItems().addAll(memberList);
        
    }
    
}
