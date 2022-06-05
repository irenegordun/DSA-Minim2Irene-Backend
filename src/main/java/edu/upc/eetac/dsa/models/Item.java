package edu.upc.eetac.dsa.models;

import edu.upc.eetac.dsa.util.RandomUtils;

public class Item {
    private String id;
    private String name;
    private String description;
    private int price;
    private String type;
    private int damage;
    private int dmgReduction;

    public Item() {}

    public Item(String name, String description, int price, String type, int damage, int dmgReduction) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.damage = damage;
        this.dmgReduction = dmgReduction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDmgReduction() {
        return dmgReduction;
    }

    public void setDmgReduction(int dmgReduction) {
        this.dmgReduction = dmgReduction;
    }

    @Override
    public String toString() {
        return "Item{ID: "+ this.id + '\'' +
                ", name ='" + this.name + '\'' +
                ", type ='" + this.type + '\'' +
                ", price ='" + this.price + '\'' +
                ", description ='" + this.description + '\'' +
                ", damage ='" + this.damage + '\'' +
                ", dmgReduction ='" + this.dmgReduction + '\'' +
                '}';

    }
}
