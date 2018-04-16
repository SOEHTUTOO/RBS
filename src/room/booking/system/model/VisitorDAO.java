/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import room.booking.system.database.Database;

/**
 *
 * @author soehtutoo
 */
public class VisitorDAO {
    
    public void addVisitorToDB (Visitor visitor) throws SQLException{
    
        String name = visitor.getName();
        Date birth = visitor.getBirth();
        Date arrival = visitor.getArrival();
        Date departure = visitor.getDeparture();
        String passport = visitor.getPassport();
        String visa = visitor.getVisa();
        String nation = visitor.getNation();
        String organization = visitor.getOrganization();
        String mobile = visitor.getMobile();
        String email = visitor.getEmail();
        String gender = visitor.getGender();
        String address = visitor.getAddress();
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String addVisitorSql = "insert into rbsdb.visitors (name,birth,arrival,departure,passport,visa,nation,organization,mobile,email,gender,address) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement visitorStmt = connectionToDB.prepareStatement(addVisitorSql);
        visitorStmt.setString(1, name);
        visitorStmt.setDate(2, birth);
        visitorStmt.setDate(3, arrival);
        visitorStmt.setDate(4, departure);
        visitorStmt.setString(5, passport);
        visitorStmt.setString(6, visa);
        visitorStmt.setString(7, nation);
        visitorStmt.setString(8, organization);
        visitorStmt.setString(9, mobile);
        visitorStmt.setString(10, email);
        visitorStmt.setString(11, gender);
        visitorStmt.setString(12, address);
        visitorStmt.execute();
        
    }
    
}
