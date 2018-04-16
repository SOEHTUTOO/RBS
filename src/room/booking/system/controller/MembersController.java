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
        
        ObservableList<Member> memberList = null;
        
        try {
            memberList = memberDAO.getMemberList();
        } catch (SQLException ex) {
            Logger.getLogger(MembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        memberTable.getItems().addAll(memberList);
        
    }    
    
}
