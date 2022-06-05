package edu.upc.eetac.dsa.dao.impl;

import edu.upc.eetac.dsa.dao.InventoryDAO;
import edu.upc.eetac.dsa.dao.ItemDAO;
import edu.upc.eetac.dsa.dao.UserDAO;
import edu.upc.eetac.dsa.models.Inventory;
import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.User;

import java.beans.IntrospectionException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class InventoryDAOImpl implements InventoryDAO {
    static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private static InventoryDAOImpl manager;
    private static UserDAO userManager = UserDAOImpl.getInstance();
    private static ItemDAO itemManager = ItemDAOImpl.getInstance();
    private SessionImpl session;

    InventoryDAOImpl() {
        this.session = SessionImpl.getInstance();
    }

    public static InventoryDAOImpl getInstance() {

        if(manager == null) {

            manager = new InventoryDAOImpl();
        }
        return manager;
    }

    // FET
    @Override
    public List<Inventory> getAll() {
        List<Inventory> inventories = this.session.getAll(Inventory.class);
        return inventories;
    }

    // FET
    @Override
    public boolean alreadyExists(String username, String itemname) {
        List<Inventory> all = this.getAll();
        User user = userManager.getUserByName(username);
        Item item = itemManager.getItemByName(itemname);

        for (Inventory i : all) {
            if ((i.getUserId().equals(user.getId())) && (i.getItemId().equals(item.getId()))) {
                return true;
            }
        }
        return false;
    }

    // NOT OK
    @Override
    public Inventory addInventory(String username, String itemname) {
        User user = (User) this.session.getByName(User.class, username);
        Item item = (Item) this.session.getByName(Item.class, itemname);

        Inventory i = new Inventory(user.getId(), item.getId());
        this.session.save(i);
        return i;
    }

    // OK
    @Override
    public List<Item> getUserInventory(String userid) {
        List<Inventory> inventories = this.getAll();
        List<Item> userItems = new LinkedList<>();

        for (Inventory i : inventories) {
            if (i.getUserId().equals(userid)) {
                Item item = (Item) this.session.getById(Item.class, i.getItemId());
                userItems.add(item);
            }
        }
        return userItems;
    }

    // FET
    @Override
    public void makeActive(String userid, String itemid) {
        List <Inventory> iList = this.getAll();
        iList.forEach(i -> {
            if(i.getItemId().equals(itemid) && i.getUserId().equals(userid)) {
                //i.setActive(true);
                try {
                    this.session.update(i);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // NOT OK
    @Override
    public void buyItem(String username, String itemname) throws IntrospectionException {
        User user = (User) this.session.getByName(User.class, username);
        Item item = (Item) this.session.getByName(Item.class, itemname);

        int coinBalance = user.getCoins() - item.getPrice();
        user.setCoins(coinBalance);
        this.session.update(user);

        this.addInventory(username, itemname);
    }
}
