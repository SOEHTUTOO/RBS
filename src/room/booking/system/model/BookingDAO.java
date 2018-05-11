/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import room.booking.system.database.Database;

/**
 *
 * @author spell
 */
public class BookingDAO {

    public void saveToRecordDB(String roomID, String passport, String memberID) throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection(); 
        
        String sql = "insert into rbsdb.records (room_id,visitor_passport,member_id) values (?,?,?)";
        PreparedStatement stmt = connectionToDB.prepareStatement(sql);
        stmt.setString(1, roomID);
        stmt.setString(2, passport);
        stmt.setString(3, memberID);
        stmt.execute();
        
    }
    
}
