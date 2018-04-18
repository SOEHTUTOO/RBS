/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.main;

import java.sql.SQLException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import room.booking.system.database.Database;

/**
 *
 * @author soehtutoo
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/room/booking/system/view/home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ARE YOU SURE?");
                alert.setHeaderText(null);
                alert.setContentText("YOU ARE GOING TO EXIT. ARE YOU SURE?");
                Optional<ButtonType> choice = alert.showAndWait();
                
                if(choice.get().equals(ButtonType.CANCEL)){
                    event.consume();
                } 
            }
        });
        
        stage.getIcons().add(new Image("/room/booking/system/icon/appLogo.png"));
        
        stage.setScene(scene);
        stage.setTitle("ROOM BOOKING SYSTEM");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        
        try {
            Database.getInstance();
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Connecting");
            alert.setContentText("Cannot Connect To The Database.");
            alert.setHeaderText(null);
            alert.show();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
}
