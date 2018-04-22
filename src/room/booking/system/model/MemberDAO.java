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
public class MemberDAO {
    
    public void addMemberToDB(Member member) throws SQLException{
    
        String name = member.getName();
        Date birth = member.getBirth();
        String id = member.getIdNo();
        String arcNo = member.getArcNo();
        String mobile = member.getMobile();
        String email = member.getEmail();
        String gender = member.getGender();
        String address = member.getAddress();
        
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String addMemberSql = "insert into rbsdb.members (name,birth,id,arc,mobile,email,gender,address) values (?,?,?,?,?,?,?,?)";
        PreparedStatement memberStmt = connectionToDB.prepareStatement(addMemberSql);
        memberStmt.setString(1, name);
        memberStmt.setDate(2, birth);
        memberStmt.setString(3, id);
        memberStmt.setString(4, arcNo);
        memberStmt.setString(5, mobile);
        memberStmt.setString(6, email);
        memberStmt.setString(7, gender);
        memberStmt.setString(8, address);
        memberStmt.execute();
    }

    public ObservableList<Member> getMemberList() throws SQLException {
        
       Connection connectionToDB = Database.getInstance().getConnection();
        
        ObservableList<Member> memberList = FXCollections.observableArrayList();
        
        String memberListSql = "select * from rbsdb.members";
        
        try {
            Statement memberListStmt = connectionToDB.createStatement();
            ResultSet memberResults = memberListStmt.executeQuery(memberListSql);
            
            while (memberResults.next()){
            
                String name = memberResults.getString("name");
                Date birth = memberResults.getDate("birth");
                String idNo = memberResults.getString("id");
                String arcNo = memberResults.getString("arc");
                String mobile = memberResults.getString("mobile");
                String email = memberResults.getString("email");
                String gender = memberResults.getString("gender");
                String address = memberResults.getString("address");
                
                Member member = new Member(name,birth,idNo,arcNo,mobile,email,gender,address);
                
                memberList.add(member);
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        
    
        return memberList; 
       
    }

    public ObservableList<Member> searchMembers(String searchText) throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        ObservableList<Member> memberList = FXCollections.observableArrayList();
        
        String searchMemberSql = "select * from rbsdb.members where name like '%"+searchText+"%'";
        Statement memberStmt = connectionToDB.createStatement();
        ResultSet memberResults = memberStmt.executeQuery(searchMemberSql);
        
        while (memberResults.next()){
            
                String name = memberResults.getString("name");
                Date birth = memberResults.getDate("birth");
                String idNo = memberResults.getString("id");
                String arcNo = memberResults.getString("arc");
                String mobile = memberResults.getString("mobile");
                String email = memberResults.getString("email");
                String gender = memberResults.getString("gender");
                String address = memberResults.getString("address");
                
                Member member = new Member(name,birth,idNo,arcNo,mobile,email,gender,address);
                
                memberList.add(member);
                
            
            }
        
        return memberList;
        
    }

    public void deleteMember(String id) throws SQLException {
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String deleteSql = "delete from rbsdb.members where id=?";
        PreparedStatement deleteStmt = connectionToDB.prepareStatement(deleteSql);
        deleteStmt.setString(1, id);
        deleteStmt.execute();
        
    }

    public void updateMemberInfo(Member memberInfo, String whereId) throws SQLException {
        
        String name = memberInfo.getName();
        Date birth = memberInfo.getBirth();
        String id = memberInfo.getIdNo();
        String arc = memberInfo.getArcNo();
        String mobile = memberInfo.getMobile();
        String email = memberInfo.getEmail();
        String gender = memberInfo.getGender();
        String address = memberInfo.getAddress();
        
        Connection connectionToDB = Database.getInstance().getConnection();
        
        String updateSql = "update rbsdb.members set name=?, birth=?, id=?, arc=?, mobile=?, email=?, gender=?, address=? where id=?";
        PreparedStatement updateStmt = connectionToDB.prepareStatement(updateSql);
        updateStmt.setString(1, name);
        updateStmt.setDate(2, birth);
        updateStmt.setString(3, id);
        updateStmt.setString(4, arc);
        updateStmt.setString(5, mobile);
        updateStmt.setString(6, email);
        updateStmt.setString(7, gender);
        updateStmt.setString(8, address);
        updateStmt.setString(9, whereId);
        updateStmt.execute();
        
    }
    
}
