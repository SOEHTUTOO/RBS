/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import room.booking.system.model.Member;
import room.booking.system.model.MemberDAO;
import room.booking.system.model.Visitor;

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
    @FXML
    private ContextMenu memberContextMenu;
    @FXML
    private MenuItem detailMenu;
    @FXML
    private MenuItem editMenu;
    @FXML
    private MenuItem deleteMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
        memberContextMenu.setStyle("-fx-background-color: #9FA8DA; -fx-text-fill: white; -fx-font-weight:bold; -fx-font-size:13px;");
        
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
        
        refreshList();
        
    }

    @FXML
    private void showMemberDetail(ActionEvent event) throws SQLException {
        
        Member member = memberTable.getSelectionModel().getSelectedItem();

        ObservableList<Member> members = memberDAO.searchMembers(member.getName());
        
        Label nameData = creatDataLb();
        Label birthData = creatDataLb();
        Label idData = creatDataLb();
        Label arcData = creatDataLb();
        Label mobileData = creatDataLb();
        Label emailData = creatDataLb();
        Label genderData = creatDataLb();
        Label addressData = creatDataLb(); 
        addressData.setWrapText(true);
        
        HBox nameBox = createDetailHBox("NAME", nameData);
        HBox birthBox = createDetailHBox("BIRTH DATE", birthData);
        HBox idBox = createDetailHBox("PASSPORT", idData);
        HBox arcBox = createDetailHBox("VISA", arcData);
        HBox mobileBox = createDetailHBox("MOBILE", mobileData);
        HBox emailBox = createDetailHBox("EMAIL", emailData);
        HBox genderBox = createDetailHBox("GENDER", genderData);
        HBox addressBox = createDetailHBox("ADDRESS", addressData);
        
        
        VBox vbox = new VBox();
        setVBox(vbox);
        vbox.getChildren().add(nameBox);
        vbox.getChildren().add(birthBox);
        vbox.getChildren().add(idBox);
        vbox.getChildren().add(arcBox);
        vbox.getChildren().add(mobileBox);
        vbox.getChildren().add(emailBox);
        vbox.getChildren().add(genderBox);
        vbox.getChildren().add(addressBox);
        
        for(Member result: members){
        
            nameData.setText(result.getName());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-YYYY", Locale.ENGLISH);
            String birthStr = dateFormat.format(result.getBirth());
            birthData.setText(birthStr);
            
            idData.setText(result.getIdNo());
            arcData.setText(result.getArcNo());
            mobileData.setText(result.getMobile());
            emailData.setText(result.getEmail());
            genderData.setText(result.getGender());
            addressData.setText(result.getAddress());
        
        }
 
        Stage stage = new Stage();
        
        Scene scene = new Scene(vbox, 400, 350);
        
        stage.getIcons().add(new Image("/room/booking/system/icon/members.png"));
        stage.setScene(scene);
        stage.setTitle("Info");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
    }

    @FXML
    private void editMemberInfo(ActionEvent event) throws SQLException {
        
        TextField nameField = createDataField();
        HBox nameBox = createEditHBox("NAME", nameField);

        DatePicker birthPicker = new DatePicker();
        birthPicker.setPrefWidth(250);
        HBox birthBox = createDateHBox("BIRTH DATE", birthPicker);
        
        TextField idField = createDataField();
        HBox idBox = createEditHBox("ID NUMBER", idField);
        
        TextField arcField = createDataField();
        HBox arcBox = createEditHBox("ARC NUMBER", arcField);
        
        TextField mobileField = createDataField();
        HBox mobileBox = createEditHBox("MOBILE", mobileField);
                
        TextField emailField = createDataField();
        HBox emailBox = createEditHBox("EMAIL", emailField);
                
        CheckBox male = new CheckBox("MALE");
        CheckBox female = new CheckBox("FEMALE");
        HBox genderBox = createCheckHBox("GENDER", male, female);
        
        TextArea addressArea = new TextArea();
        addressArea.setPrefWidth(250);
        addressArea.setWrapText(true);
        HBox addressBox = createAddressHBox("ADDRESS", addressArea);
        
        JFXButton saveBtn = new JFXButton("SAVE");
        saveBtn.setStyle("-fx-background-color: #009688; -fx-pref-height: 25px; -fx-pref-width: 90px; -fx-border-width: 2px; -fx-border-color: #004D40; -fx-font-size: 13pt; -fx-text-fill: #A7FFEB");
        JFXButton deleteBtn = new JFXButton("DELETE");
        deleteBtn.setStyle("-fx-background-color: #E91E63; -fx-pref-height: 25px; -fx-pref-width: 90px; -fx-border-width: 2px; -fx-border-color: #880E4F; -fx-font-size: 13pt; -fx-text-fill: #FCE4EC");
        JFXButton closeBtn = new JFXButton("CLOSE");
        closeBtn.setStyle("-fx-background-color: #ffffff; -fx-pref-height: 25px; -fx-pref-width: 90px; -fx-border-width: 2px; -fx-border-color: #000000; -fx-font-size: 13pt; -fx-text-fill: #000000");
        
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);
	btnBox.setPadding(new Insets(0,0,0,0));
	btnBox.setSpacing(10);
        btnBox.getChildren().add(saveBtn);
        btnBox.getChildren().add(deleteBtn);
        btnBox.getChildren().add(closeBtn);
        
        VBox vbox = new VBox();
        setVBox(vbox);
        vbox.getChildren().add(nameBox);
        vbox.getChildren().add(birthBox);
        vbox.getChildren().add(idBox);
        vbox.getChildren().add(arcBox);
        vbox.getChildren().add(mobileBox);
        vbox.getChildren().add(emailBox);
        vbox.getChildren().add(genderBox);
        vbox.getChildren().add(addressBox);
        vbox.getChildren().add(btnBox);
        
        Member member = memberTable.getSelectionModel().getSelectedItem();

        ObservableList<Member> members = memberDAO.searchMembers(member.getName());
        
        for(Member result: members){
        
        nameField.setText(result.getName());
        birthPicker.setValue(result.getBirth().toLocalDate());
        idField.setText(result.getIdNo());
        arcField.setText(result.getArcNo());
        mobileField.setText(result.getMobile());
        emailField.setText(result.getEmail());
        if(result.getGender().equals("Male")){
            male.setSelected(true);
        }else if(result.getGender().equals("Female")){
            female.setSelected(true);
        }else{
            return;
        }
        addressArea.setText(result.getAddress());
        
        }
        
        Stage stage = new Stage();
        
        closeBtn.setOnAction(e->{ 

                stage.close();
				
        });
        
        saveBtn.setOnAction(e->{ 

            String name = nameField.getText();
            Date birth = java.sql.Date.valueOf(birthPicker.getValue());
            String id = idField.getText();
            String arc = arcField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();
            String gender = null;
            
            if(male.isSelected() && female.isSelected()) {
      
                gender = null;
            
            }else if(male.isSelected()){
        
                gender = "Male";
            
            }else if(female.isSelected()){
        
                gender = "Female";
            
            }else{
        
                gender = null;
            }
            
            String address = addressArea.getText();
            
            if(name.isEmpty() || birth==null || id.isEmpty() || arc.isEmpty() || mobile.isEmpty() || email.isEmpty() || gender==null || address.isEmpty()){
                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check The Info");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.show();
            return;
        }
            
            Member memberInfo = new Member(name,birth,id,arc,mobile,email,gender,address);
        
            
            try {   
                memberDAO.updateMemberInfo(memberInfo,id);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText(null);
                alert.setContentText("SUCCESS.");
                alert.show();
                
            } catch (SQLException ex) {
                
            }
            
            refreshList();
            
        });
        
        deleteBtn.setOnAction(e->{ 

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("DELETE THE INFO?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    
                    try {
                String id = idField.getText();
                
                memberDAO.deleteMember(id);
                
                stage.close();
                
                refreshList();
            } catch (SQLException ex) {
                
            }
                    
        }else{}
				
        });
        
        
        Scene scene = new Scene(vbox, 400, 400);
        
        stage.getIcons().add(new Image("/room/booking/system/icon/members.png"));
        stage.setScene(scene);
        stage.setTitle("Edit Info");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
    }

    @FXML
    private void deleteMember(ActionEvent event) {
        
        Member member = memberTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("DELETE THE INFO?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    
                    try {
                        String id = member.getIdNo();
                        
                        memberDAO.deleteMember(id);
                
                        refreshList();
                        
                        } catch (SQLException ex) {
                
            }
                    
        }
        
    }
    
    private void refreshList(){
    
        ObservableList<Member> memberList = null;
        try {
            memberList = memberDAO.getMemberList();
        } catch (SQLException ex) {
        }
        
        for ( int i = 0; i<memberTable.getItems().size(); i++) {
            memberTable.getItems().clear();
        }
        
        memberTable.getItems().addAll(memberList);
        
    }
    
    private Label creatDataLb(){
        
        Label label = new Label("");
        label.setPrefWidth(230);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
   
        return label;
    }
    
    private HBox createDetailHBox (String name, Label data) {
        
        Label textLabel = new Label(name);
        textLabel.setPrefWidth(150);
        textLabel.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
    
        HBox hbox = new HBox();
        
        hbox.setAlignment(Pos.TOP_LEFT);
	hbox.setPadding(new Insets(0,0,0,0));
	hbox.setSpacing(10);
        hbox.getChildren().add(textLabel);
                
        Label dash = new Label(">");
        dash.setPrefWidth(20);
        dash.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        hbox.getChildren().add(dash);
        
        hbox.getChildren().add(data);
        
        return hbox;
        
    }
    
    private void setVBox (VBox vbox) {
    
        vbox.setAlignment(Pos.TOP_LEFT);
	vbox.setPadding(new Insets(20,20,20,20));
	vbox.setSpacing(10);
        
    }
    
    private HBox createEditHBox(String name, TextField data){
    
        Label label = new Label(name);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
	hbox.setPadding(new Insets(0,0,0,0));
	hbox.setSpacing(10);
        hbox.getChildren().add(label);
        hbox.getChildren().add(data);
        
        return hbox;
    }
    
    private HBox createDateHBox (String name, DatePicker data){
    
        Label label = new Label(name);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
	hbox.setPadding(new Insets(0,0,0,0));
	hbox.setSpacing(10);
        hbox.getChildren().add(label);
        hbox.getChildren().add(data);
        
        return hbox;
        
    }
    
    private HBox createCheckHBox(String name, CheckBox male, CheckBox female){
    
        Label label = new Label(name);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
	hbox.setPadding(new Insets(0,0,0,0));
	hbox.setSpacing(10);
        hbox.getChildren().add(label);
        hbox.getChildren().add(male);
        hbox.getChildren().add(female);
        
        return hbox;
        
    }
    
    private HBox createAddressHBox (String name, TextArea data){
    
        Label label = new Label(name);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
	hbox.setPadding(new Insets(0,0,0,0));
	hbox.setSpacing(10);
        hbox.getChildren().add(label);
        hbox.getChildren().add(data);
        
        return hbox;
    
    }
    
    private TextField createDataField(){
    
        TextField textField = new TextField();
        textField.setPrefWidth(250);
        
        return textField;
    }
}
