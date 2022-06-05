package edu.upc.eetac.dsa.dao.impl;

import edu.upc.eetac.dsa.dao.ItemDAO;
import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.User;

import java.util.List;
import java.util.logging.Logger;

public class ItemDAOImpl implements ItemDAO {

    //UserDAO userManager = UserDAOImpl.getInstance();
    static final Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());
    private static ItemDAOImpl manager;
    private SessionImpl session;

    private  ItemDAOImpl() {
        this.session = SessionImpl.getInstance();
    }

    public static ItemDAOImpl getInstance() {

        if(manager == null) {

            manager = new ItemDAOImpl();
        }
        return manager;
    }

    // OK
    @Override
    public List<Item> getStoreList() {
        List<Item> storeList = this.session.getAll(Item.class);
        return storeList;
    }

    // FET
    public Item addItem(Item item) {
        this.session.save(item);
        logger.info("Item saved: " + item.toString());
        return item;
    }

    // FET
    @Override
    public Item getItemByName(String itemname) {
        Item item = (Item) this.session.getByName(Item.class, itemname);
        if (item.getName() == null) return null;
        logger.info("item by name: " + item.toString());
        return item;
    }
}
