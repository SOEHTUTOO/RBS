/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public ObservableList<Visitor> getVisitorList() throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        ObservableList<Visitor> visitorList = FXCollections.observableArrayList();
        
        String visitorListSql = "select * from rbsdb.visitors";
        
        try {
            Statement visitorListStmt = connectionToDB.createStatement();
            ResultSet visitorResults = visitorListStmt.executeQuery(visitorListSql);
            
            while (visitorResults.next()){
            
                String name = visitorResults.getString("name");
                Date birth = visitorResults.getDate("birth");
                Date arrival = visitorResults.getDate("arrival");
                Date departure = visitorResults.getDate("departure");
                String passport = visitorResults.getString("passport");
                String visa = visitorResults.getString("visa");
                String nation = visitorResults.getString("nation");
                String organization = visitorResults.getString("organization");
                String mobile = visitorResults.getString("mobile");
                String email = visitorResults.getString("email");
                String gender = visitorResults.getString("gender");
                String address = visitorResults.getString("address");
                
                Visitor visitor = new Visitor(name,birth,arrival,departure,passport,visa,nation,organization,mobile,email,gender,address);
                
                visitorList.add(visitor);
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        
    
        return visitorList;
        
    }

    public ObservableList<Visitor> searchVisitors(String searchText) throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        ObservableList<Visitor> visitorList = FXCollections.observableArrayList();
        
        String searchVisitorSql = "select * from rbsdb.visitors where name like '%"+searchText+"%'";
        Statement visitorStmt = connectionToDB.createStatement();
        ResultSet visitorResults = visitorStmt.executeQuery(searchVisitorSql);
        
        while (visitorResults.next()){
            
                String name = visitorResults.getString("name");
                Date birth = visitorResults.getDate("birth");
                Date arrival = visitorResults.getDate("arrival");
                Date departure = visitorResults.getDate("departure");
                String passport = visitorResults.getString("passport");
                String visa = visitorResults.getString("visa");
                String nation = visitorResults.getString("nation");
                String organization = visitorResults.getString("organization");
                String mobile = visitorResults.getString("mobile");
                String email = visitorResults.getString("email");
                String gender = visitorResults.getString("gender");
                String address = visitorResults.getString("address");
                
                Visitor visitor = new Visitor(name,birth,arrival,departure,passport,visa,nation,organization,mobile,email,gender,address);
                
                visitorList.add(visitor);
        }
        
        return visitorList;
        
    }

    public void updateVistorInfo(Visitor visitorInfo, String wherePassport) throws SQLException {
        
        String name = visitorInfo.getName();
        Date birth = visitorInfo.getBirth();
        Date arrival = visitorInfo.getArrival();
        Date departure = visitorInfo.getDeparture();
        String passport = visitorInfo.getPassport();
        String visa = visitorInfo.getVisa();
        String nation = visitorInfo.getNation();
        String orgnize = visitorInfo.getOrganization();
        String mobile = visitorInfo.getMobile();
        String email = visitorInfo.getEmail();
        String gender = visitorInfo.getGender();
        String address = visitorInfo.getAddress();
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String updateSql = "update rbsdb.visitors set name=?, birth=?, arrival=?, departure=?, passport=?, visa=?, nation=?, organization=?, mobile=?, email=?, gender=?, address=? where passport=?";
        PreparedStatement updateStmt = connectionToDB.prepareStatement(updateSql);
        updateStmt.setString(1, name);
        updateStmt.setDate(2, birth);
        updateStmt.setDate(3, arrival);
        updateStmt.setDate(4, departure);
        updateStmt.setString(5, passport);
        updateStmt.setString(6, visa);
        updateStmt.setString(7, nation);
        updateStmt.setString(8, orgnize);
        updateStmt.setString(9, mobile);
        updateStmt.setString(10, email);
        updateStmt.setString(11, gender);
        updateStmt.setString(12, address);
        updateStmt.setString(13, wherePassport);
        updateStmt.execute();
        
    }

    public void deleteVisitor(String passport) throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String deleteSql = "delete from rbsdb.visitors where passport=?";
        PreparedStatement deleteStmt = connectionToDB.prepareStatement(deleteSql);
        deleteStmt.setString(1, passport);
        deleteStmt.execute();
        
    }
    
}
