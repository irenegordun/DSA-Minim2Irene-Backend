package edu.upc.eetac.dsa.dao;

import edu.upc.eetac.dsa.models.Item;

import java.util.List;

public interface ItemDAO {
    public List<Item> getStoreList();
    public Item addItem(Item item);
    public Item getItemByName(String itemname);
}
