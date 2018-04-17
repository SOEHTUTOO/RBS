/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author soehtutoo
 */
public class Database {
    
    private Connection connectionToDB;
    
    private static Database database;
    
    
    private Database() throws SQLException {

        connect();
        createDB();
        createTB();
        
    }
    
    public static Database getInstance () throws SQLException {
        
        if(database==null){
            database = new Database();
        }
        return database;
    }
    
    private void connect() throws SQLException {
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            
        }
        
        connectionToDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
        
    }
    
    private void createDB​() throws SQLException{
    
        String createDBSql = "create database if not exists rbsdb";
        Statement creatDBStmt = connectionToDB.createStatement();
        creatDBStmt.execute(createDBSql);
        
    }
    
    private void createTB () throws SQLException {
    
        String createRoomTB = "create table if not exists rbsdb.rooms (id varchar(50) primary key, slot int, building varchar(50), is_available boolean default true)";
        String createVisitorTB = "create table if not exists rbsdb.visitors (name varchar(100), birth date, arrival date, departure date, passport varchar(50) primary key, visa varchar(50), nation varchar(100), organization varchar(100), mobile varchar(30), email varchar(50), gender varchar(10), address varchar(255))";
        String createMemberTB = "create table if not exists rbsdb.members (name varchar(100), birth date, id varchar(30) primary key, arc varchar(15), mobile varchar(30), email varchar(50), gender varchar(10), address varchar(255))";
        String createRecordTB = "create table if not exists rbsdb.records (room_id varchar(50), visitor_name varchar(100), member_name varchar(100),  foreign key (room_id) references rooms(id), foreign key (visitor_name) references visitors(name), foreign key (member_name) references members(name))";
        
        Statement createStmt = connectionToDB.createStatement();
        
        createStmt.execute(createRoomTB);
        createStmt.execute(createVisitorTB);
        createStmt.execute(createMemberTB);
        //createStmt.execute(createRecordTB);
        
        
    }
    
    public Connection getConnection(){
        
        return connectionToDB;
        
    }
    
    
    
}
