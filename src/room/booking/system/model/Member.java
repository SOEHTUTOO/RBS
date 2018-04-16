/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

import java.sql.Date;

/**
 *
 * @author SPELL
 */
public class Member {
    
    private String name;
    private Date birth;
    private String idNo;
    private String arcNo;
    private String gender;
    private String email;
    private String mobile;
    private String address;

    public Member(String name, Date birth, String idNo, String arcNo, String gender, String email, String mobile, String address) {
        this.name = name;
        this.birth = birth;
        this.idNo = idNo;
        this.arcNo = arcNo;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getArcNo() {
        return arcNo;
    }

    public void setArcNo(String arcNo) {
        this.arcNo = arcNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
