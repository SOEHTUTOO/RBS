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
    @FXML
    private MenuItem detailMenu;
    @FXML
    private MenuItem editMenu;
    @FXML
    private MenuItem addRoomMenu;
    @FXML
    private TableColumn<Integer, Integer> noCol;
    @FXML
    private ContextMenu visitorContextMenu;
    @FXML
    private MenuItem deleteMenu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        visitorDAO = new VisitorDAO();
        
        visitorContextMenu.setStyle("-fx-background-color: #9FA8DA; -fx-text-fill: white; -fx-font-weight:bold; -fx-font-size:13px;");
        
        visitorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passport"));
        nationCol.setCellValueFactory(new PropertyValueFactory<>("nation"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        noCol.setSortable(false);
        noCol.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(visitorTable.getItems().indexOf(column.getValue())+1));
        
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
        
        refreshList();
        
    }

    @FXML
    private void showDetail(ActionEvent event) throws SQLException {
        
        Visitor visitor = visitorTable.getSelectionModel().getSelectedItem();

        ObservableList<Visitor> visitors = visitorDAO.searchVisitors(visitor.getName());
        
        Label nameData = creatDataLb();
        Label birthData = creatDataLb();
        Label arrivalData = creatDataLb();
        Label departureData = creatDataLb();
        Label passportData = creatDataLb();
        Label visaData = creatDataLb();
        Label nationData = creatDataLb();
        Label organizationData = creatDataLb();
        Label mobileData = creatDataLb();
        Label emailData = creatDataLb();
        Label genderData = creatDataLb();
        Label addressData = creatDataLb(); 
        addressData.setWrapText(true);
        
        HBox nameBox = createDetailHBox("NAME", nameData);
        HBox birthBox = createDetailHBox("BIRTH DATE", birthData);
        HBox arrivalBox = createDetailHBox("ARRIVAL DATE", arrivalData);
        HBox departureBox = createDetailHBox("DEPARTURE DATE", departureData);
        HBox passportBox = createDetailHBox("PASSPORT", passportData);
        HBox visaBox = createDetailHBox("VISA", visaData);
        HBox nationBox = createDetailHBox("NATIONALITY", nationData);
        HBox organizationBox = createDetailHBox("ORGANIZATION", organizationData);
        HBox mobileBox = createDetailHBox("MOBILE", mobileData);
        HBox emailBox = createDetailHBox("EMAIL", emailData);
        HBox genderBox = createDetailHBox("GENDER", genderData);
        HBox addressBox = createDetailHBox("ADDRESS", addressData);
        
        
        VBox vbox = new VBox();
        setVBox(vbox);
        vbox.getChildren().add(nameBox);
        vbox.getChildren().add(birthBox);
        vbox.getChildren().add(arrivalBox);
        vbox.getChildren().add(departureBox);
        vbox.getChildren().add(passportBox);
        vbox.getChildren().add(visaBox);
        vbox.getChildren().add(nationBox);
        vbox.getChildren().add(organizationBox);
        vbox.getChildren().add(mobileBox);
        vbox.getChildren().add(emailBox);
        vbox.getChildren().add(genderBox);
        vbox.getChildren().add(addressBox);
        
        for(Visitor result: visitors){
        
            
            nameData.setText(result.getName());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-YYYY", Locale.ENGLISH);
            String birthStr = dateFormat.format(result.getBirth());
            String arrivalStr = dateFormat.format(result.getArrival());
            String departureStr = dateFormat.format(result.getDeparture());
            birthData.setText(birthStr);
            arrivalData.setText(arrivalStr);
            departureData.setText(departureStr);
            
            passportData.setText(result.getPassport());
            visaData.setText(result.getVisa());
            nationData.setText(result.getNation());
            organizationData.setText(result.getOrganization());
            mobileData.setText(result.getMobile());
            emailData.setText(result.getEmail());
            genderData.setText(result.getGender());
            addressData.setText(result.getAddress());
        
        }
        
        Stage stage = new Stage();
        
        Scene scene = new Scene(vbox, 400, 450);
        
        stage.getIcons().add(new Image("/room/booking/system/icon/visitors.png"));
        stage.setScene(scene);
        stage.setTitle("Info");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
    }

    @FXML
    private void editVisitorInfo(ActionEvent event) throws SQLException {
        
        
        TextField nameField = createDataField();
        HBox nameBox = createEditHBox("NAME", nameField);

        DatePicker birthPicker = new DatePicker();
        birthPicker.setPrefWidth(250);
        HBox birthBox = createDateHBox("BIRTH DATE", birthPicker);
        
        DatePicker arrivalPicker = new DatePicker();
        arrivalPicker.setPrefWidth(250);
        HBox arrivalBox = createDateHBox("ARRIVAL DATE", arrivalPicker);
        
        DatePicker departurePicker = new DatePicker();
        departurePicker.setPrefWidth(250);
        HBox departureBox = createDateHBox("DEPARTURE DATE", departurePicker);
        
        TextField passportField = createDataField();
        HBox passportBox = createEditHBox("PASSPORT", passportField);
        
        TextField visaField = createDataField();
        HBox visaBox = createEditHBox("VISA", visaField);
        
        TextField nationField = createDataField();
        HBox nationBox = createEditHBox("NATIONALITY", nationField);
                
        TextField organizeField = createDataField();
        HBox orgnizeBox = createEditHBox("ORGANIZATION", organizeField);
                
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
        vbox.getChildren().add(arrivalBox);
        vbox.getChildren().add(departureBox);
        vbox.getChildren().add(passportBox);
        vbox.getChildren().add(visaBox);
        vbox.getChildren().add(nationBox);
        vbox.getChildren().add(orgnizeBox);
        vbox.getChildren().add(mobileBox);
        vbox.getChildren().add(emailBox);
        vbox.getChildren().add(genderBox);
        vbox.getChildren().add(addressBox);
        vbox.getChildren().add(btnBox);
        
        Visitor visitor = visitorTable.getSelectionModel().getSelectedItem();

        ObservableList<Visitor> visitors = visitorDAO.searchVisitors(visitor.getName());
        
        for(Visitor result: visitors){
        
        nameField.setText(result.getName());
        birthPicker.setValue(result.getBirth().toLocalDate());
        arrivalPicker.setValue(result.getArrival().toLocalDate());
        departurePicker.setValue(result.getDeparture().toLocalDate());
        passportField.setText(result.getPassport());
        visaField.setText(result.getVisa());
        nationField.setText(result.getNation());
        organizeField.setText(result.getOrganization());
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
            Date arrival = java.sql.Date.valueOf(arrivalPicker.getValue());
            Date departure = java.sql.Date.valueOf(departurePicker.getValue());
            String passport = passportField.getText();
            String visa = visaField.getText();
            String nation = nationField.getText();
            String organize = organizeField.getText();
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
            
            if(name.isEmpty() || birth==null || arrival==null || departure==null || passport.isEmpty() || visa.isEmpty() || nation.isEmpty() || organize.isEmpty() || mobile.isEmpty() || email.isEmpty() || gender==null || address.isEmpty()){
                
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check The Info");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.show();
            return;
        }
            
            Visitor visitorInfo = new Visitor(name,birth,arrival,departure,passport,visa,nation,organize,mobile,email,gender,address);
        
            
            try {   
                visitorDAO.updateVistorInfo(visitorInfo,passport);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText(null);
                alert.setContentText("SUCCESS.");
                alert.show();
                
            } catch (SQLException ex) {
                Logger.getLogger(VisitorsController.class.getName()).log(Level.SEVERE, null, ex);
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
                String passport = passportField.getText();
                
                visitorDAO.deleteVisitor(passport);
                
                stage.close();
                
                refreshList();
            } catch (SQLException ex) {
                
            }
                    
        }else{}
				
        });
        
        
        Scene scene = new Scene(vbox, 400, 500);
        
        stage.getIcons().add(new Image("/room/booking/system/icon/visitors.png"));
        stage.setScene(scene);
        stage.setTitle("Edit Info");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
    }

    @FXML
    private void addRoomTo(ActionEvent event) {
    }
    
    private void refreshList(){
    
        ObservableList<Visitor> visitorList = null;
        try {
            visitorList = visitorDAO.getVisitorList();
        } catch (SQLException ex) {
        }
        
        for ( int i = 0; i<visitorTable.getItems().size(); i++) {
            visitorTable.getItems().clear();
        }
        
        visitorTable.getItems().addAll(visitorList);
        
    }

    @FXML
    private void deleteVisitor(ActionEvent event) {
        
        Visitor visitor = visitorTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("DELETE THE INFO?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.OK)){
                    
                    try {
                        String passport = visitor.getPassport();
                
                        visitorDAO.deleteVisitor(passport);
                
                        refreshList();
                        
                        } catch (SQLException ex) {
                
            }
                    
        }
        
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
	vbox.setSpacing(5);
        
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
