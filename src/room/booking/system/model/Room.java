/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room.booking.system.model;

/**
 *
 * @author SPELL
 */
public class Room {
    
    private String id;
    private int slot;
    private String building;
    private boolean available;

    public Room(String id, int slot, String building) {
        this.id = id;
        this.slot = slot;
        this.building = building;
    }

    public Room(String id, int slot, String building, boolean available) {
        this.id = id;
        this.slot = slot;
        this.building = building;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
    
    
    
}
