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
        
        Label nameLb = new Label("");
        nameLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label birthLb = new Label("");
        birthLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label arrivalLb = new Label("");
        arrivalLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label departureLb = new Label("");
        departureLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label passportLb = new Label("");
        passportLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label visaLb = new Label("");
        visaLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label nationLb = new Label("");
        nationLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label organizationLb = new Label("");
        organizationLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label mobileLb = new Label("");
        mobileLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label emailLb = new Label("");
        emailLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label genderLb = new Label("");
        genderLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        Label addressLb = new Label("");
        addressLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        addressLb.setWrapText(true);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
	vbox.setPadding(new Insets(20,20,20,20));
	vbox.setSpacing(10);
        vbox.getChildren().add(nameLb);
        vbox.getChildren().add(birthLb);
        vbox.getChildren().add(arrivalLb);
        vbox.getChildren().add(departureLb);
        vbox.getChildren().add(passportLb);
        vbox.getChildren().add(visaLb);
        vbox.getChildren().add(nationLb);
        vbox.getChildren().add(organizationLb);
        vbox.getChildren().add(mobileLb);
        vbox.getChildren().add(emailLb);
        vbox.getChildren().add(genderLb);
        vbox.getChildren().add(addressLb);
        
        for(Visitor result: visitors){
        
            nameLb.setText("NAME\t\t\t\t\t-\t"+result.getName());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
            String birthStr = dateFormat.format(result.getBirth());
            String arrivalStr = dateFormat.format(result.getArrival());
            String departureStr = dateFormat.format(result.getDeparture());
            birthLb.setText("BIRTH DATE\t\t\t\t-\t"+birthStr);
            arrivalLb.setText("ARRIVAL DATE\t\t\t-\t"+arrivalStr);
            departureLb.setText("DEPARTURE DATE\t\t\t-\t"+departureStr);
            
            passportLb.setText("PASSPORT\t\t\t\t-\t"+result.getPassport());
            visaLb.setText("VISA\t\t\t\t\t-\t"+result.getVisa());
            nationLb.setText("NATIONALITY\t\t\t-\t"+result.getNation());
            organizationLb.setText("ORGANIZATION\t\t\t-\t"+result.getOrganization());
            mobileLb.setText("MOBILE NUMBER\t\t\t-\t"+result.getMobile());
            emailLb.setText("EMAIL\t\t\t\t\t-\t"+result.getEmail());
            genderLb.setText("GENDER\t\t\t\t\t-\t"+result.getGender());
            addressLb.setText("ADDRESS\t\t\t\t-\n"+result.getAddress());
        
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
        
        Label nameLb = new Label("NAME");
        nameLb.setPrefWidth(150);
        nameLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField nameField = new TextField();
        nameField.setPrefWidth(250);
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.TOP_LEFT);
	nameBox.setPadding(new Insets(0,0,0,0));
	nameBox.setSpacing(10);
        nameBox.getChildren().add(nameLb);
        nameBox.getChildren().add(nameField);
        
        Label birthLb = new Label("BIRTH DATE");
        birthLb.setPrefWidth(150);
        birthLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        DatePicker birthPicker = new DatePicker();
        birthPicker.setPrefWidth(250);
        HBox birthBox = new HBox();
        birthBox.setAlignment(Pos.TOP_LEFT);
	birthBox.setPadding(new Insets(0,0,0,0));
	birthBox.setSpacing(10);
        birthBox.getChildren().add(birthLb);
        birthBox.getChildren().add(birthPicker);
        
        Label arrivalLb = new Label("ARRIVAL DATE");
        arrivalLb.setPrefWidth(150);
        arrivalLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        DatePicker arrivalPicker = new DatePicker();
        arrivalPicker.setPrefWidth(250);
        HBox arrivalBox = new HBox();
        arrivalBox.setAlignment(Pos.TOP_LEFT);
	arrivalBox.setPadding(new Insets(0,0,0,0));
	arrivalBox.setSpacing(10);
        arrivalBox.getChildren().add(arrivalLb);
        arrivalBox.getChildren().add(arrivalPicker);
        
        Label departureLb = new Label("DEPARTURE DATE");
        departureLb.setPrefWidth(150);
        departureLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        DatePicker departurePicker = new DatePicker();
        departurePicker.setPrefWidth(250);
        HBox departureBox = new HBox();
        departureBox.setAlignment(Pos.TOP_LEFT);
	departureBox.setPadding(new Insets(0,0,0,0));
	departureBox.setSpacing(10);
        departureBox.getChildren().add(departureLb);
        departureBox.getChildren().add(departurePicker);
        
        Label passportLb = new Label("PASSPORT");
        passportLb.setPrefWidth(150);
        passportLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField passportField = new TextField();
        passportField.setPrefWidth(250);
        HBox passportBox = new HBox();
        passportBox.setAlignment(Pos.TOP_LEFT);
	passportBox.setPadding(new Insets(0,0,0,0));
	passportBox.setSpacing(10);
        passportBox.getChildren().add(passportLb);
        passportBox.getChildren().add(passportField);
        
        Label visaLb = new Label("VISA");
        visaLb.setPrefWidth(150);
        visaLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField visaField = new TextField();
        visaField.setPrefWidth(250);
        HBox visaBox = new HBox();
        visaBox.setAlignment(Pos.TOP_LEFT);
	visaBox.setPadding(new Insets(0,0,0,0));
	visaBox.setSpacing(10);
        visaBox.getChildren().add(visaLb);
        visaBox.getChildren().add(visaField);
        
        Label nationLb = new Label("NATIONALITY");
        nationLb.setPrefWidth(150);
        nationLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField nationField = new TextField();
        nationField.setPrefWidth(250);
        HBox nationBox = new HBox();
        nationBox.setAlignment(Pos.TOP_LEFT);
	nationBox.setPadding(new Insets(0,0,0,0));
	nationBox.setSpacing(10);
        nationBox.getChildren().add(nationLb);
        nationBox.getChildren().add(nationField);
        
        Label orgnizeLb = new Label("ORGANIZATION");
        orgnizeLb.setPrefWidth(150);
        orgnizeLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField orgnizeField = new TextField();
        orgnizeField.setPrefWidth(250);
        HBox orgnizeBox = new HBox();
        orgnizeBox.setAlignment(Pos.TOP_LEFT);
	orgnizeBox.setPadding(new Insets(0,0,0,0));
	orgnizeBox.setSpacing(10);
        orgnizeBox.getChildren().add(orgnizeLb);
        orgnizeBox.getChildren().add(orgnizeField);
        
        Label mobileLb = new Label("MOBILE");
        mobileLb.setPrefWidth(150);
        mobileLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField mobileField = new TextField();
        mobileField.setPrefWidth(250);
        HBox mobileBox = new HBox();
        mobileBox.setAlignment(Pos.TOP_LEFT);
	mobileBox.setPadding(new Insets(0,0,0,0));
	mobileBox.setSpacing(10);
        mobileBox.getChildren().add(mobileLb);
        mobileBox.getChildren().add(mobileField);
        
        Label emailLb = new Label("EMAIL");
        emailLb.setPrefWidth(150);
        emailLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);
        HBox emailBox = new HBox();
        emailBox.setAlignment(Pos.TOP_LEFT);
	emailBox.setPadding(new Insets(0,0,0,0));
	emailBox.setSpacing(10);
        emailBox.getChildren().add(emailLb);
        emailBox.getChildren().add(emailField);
        
        Label genderLb = new Label("GENDER");
        genderLb.setPrefWidth(150);
        genderLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        CheckBox male = new CheckBox("MALE");
        CheckBox female = new CheckBox("FEMALE");
        HBox genderBox = new HBox();
        genderBox.setAlignment(Pos.TOP_LEFT);
	genderBox.setPadding(new Insets(0,0,0,0));
	genderBox.setSpacing(10);
        genderBox.getChildren().add(genderLb);
        genderBox.getChildren().add(male);
        genderBox.getChildren().add(female);
        
        Label addressLb = new Label("ADDRESS");
        addressLb.setPrefWidth(150);
        addressLb.setStyle("-fx-font-weight:bold; -fx-font-size:13px; -fx-text-fill: black;");
        TextArea addressArea = new TextArea();
        addressArea.setPrefWidth(250);
        addressArea.setWrapText(true);
        HBox addressBox = new HBox();
        addressBox.setAlignment(Pos.TOP_LEFT);
	addressBox.setPadding(new Insets(0,0,0,0));
	addressBox.setSpacing(10);
        addressBox.getChildren().add(addressLb);
        addressBox.getChildren().add(addressArea);
        
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
        vbox.setAlignment(Pos.TOP_LEFT);
	vbox.setPadding(new Insets(20,20,20,20));
	vbox.setSpacing(5);
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
        orgnizeField.setText(result.getOrganization());
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
            String organize = orgnizeField.getText();
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
                    
        }
				
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
    
}
