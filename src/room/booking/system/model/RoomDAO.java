/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import room.booking.system.database.Database;

/**
 *
 * @author soehtutoo
 */
public class RoomDAO {
    
    public void addRoomToDB(Room room) throws SQLException{
       
        Connection connectionToDB = Database.getInstance().getConnection();
        String addRoomSql = "insert into rbsdb.rooms (id,slot,building) values (?,?,?)";
        PreparedStatement addRoomStmt = connectionToDB.prepareStatement(addRoomSql);
        addRoomStmt.setString(1, room.getId());
        addRoomStmt.setInt(2, room.getSlot());
        addRoomStmt.setString(3, room.getBuilding());
        addRoomStmt.execute();

        }
    
    public ObservableList<Room> getRoomList() throws SQLException{
    
        Connection connectionToDB = Database.getInstance().getConnection();
        
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        
        String roomListSql = "select * from rbsdb.rooms";
        
        try {
            Statement roomListStmt = connectionToDB.createStatement();
            ResultSet roomResults = roomListStmt.executeQuery(roomListSql);
            
            while (roomResults.next()){
            
                String id = roomResults.getString("id");
                int slot = roomResults.getInt("slot");
                String building = roomResults.getString("building");
                boolean available = roomResults.getBoolean("is_available");
                
                Room room = new Room(id,slot,building,available);
                
                roomList.add(room);
                
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        return roomList;
    }
    
}
