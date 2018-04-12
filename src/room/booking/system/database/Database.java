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
    
    public void connect() throws SQLException {
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            
        }
        
        connectionToDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
        
    }
    
    public void createDB​() throws SQLException{
    
        String createDBSql = "create database if not exists rbsdb";
        Statement creatDBStmt = connectionToDB.createStatement();
        creatDBStmt.execute(createDBSql);
        
    }
    
    public void createTB () throws SQLException {
    
        String createRoomTB = "create table if not exists rbsdb.rooms (id varchar(255) unique key, slot int, building varchar(255), is_available boolean default true)";
        
        Statement createTBStmt = connectionToDB.createStatement();
        
        createTBStmt.execute(createRoomTB);
        
    }
    
    public Connection getConnection(){
        
        return connectionToDB;
        
    }
    
    
    
}
