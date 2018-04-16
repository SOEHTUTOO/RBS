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
public class MemberDAO {
    
    public void addMemberToDB(Member member) throws SQLException{
    
        String name = member.getName();
        Date birth = member.getBirth();
        String id = member.getIdNo();
        String arcNo = member.getArcNo();
        String gender = member.getGender();
        String mobile = member.getMobile();
        String email = member.getEmail();
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
    
}
