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
public class Visitor {
    
    private String name;
    private Date birth;
    private String passport;
    private String visa;
    private String nation;
    private String organization;
    private String mobile;
    private String email;
    private String gender;
    private Date arrival;
    private Date departure;
    private String address;

    public Visitor(String name, Date birth, String passport, String visa, String nation, String organization, String mobile, String email, String gender, Date arrival, Date departure, String address) {
        this.name = name;
        this.birth = birth;
        this.passport = passport;
        this.visa = visa;
        this.nation = nation;
        this.organization = organization;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.arrival = arrival;
        this.departure = departure;
        this.address = address;
    }

    
    
    
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String number) {
        this.mobile = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
