package edu.upc.eetac.dsa.models;

import edu.upc.eetac.dsa.util.RandomUtils;

public class Inventory {
    private String id;
    private String userId;
    private String itemId;
    //private boolean active;

    public Inventory(String userId, String itemId) {
        this.id = RandomUtils.getId();
        this.userId = userId;
        this.itemId = itemId;
        //this.active = false;
    }

    public Inventory() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /*public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }*/
}
