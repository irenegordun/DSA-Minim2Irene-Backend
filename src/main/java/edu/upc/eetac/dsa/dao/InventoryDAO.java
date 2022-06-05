package edu.upc.eetac.dsa.dao;

import edu.upc.eetac.dsa.models.Inventory;
import edu.upc.eetac.dsa.models.Item;

import java.beans.IntrospectionException;
import java.util.List;

public interface InventoryDAO {
    public List<Inventory> getAll();
    public boolean alreadyExists(String username, String itemname);
    public Inventory addInventory(String username, String itemname);
    public List<Item> getUserInventory(String username);
    public void makeActive(String userid, String itemid);
    public void buyItem(String username, String itemname) throws IntrospectionException;
}
