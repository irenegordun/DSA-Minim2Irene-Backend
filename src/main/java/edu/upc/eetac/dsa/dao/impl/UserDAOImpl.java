package edu.upc.eetac.dsa.dao.impl;

import edu.upc.eetac.dsa.dao.UserDAO;
import edu.upc.eetac.dsa.models.User;

import java.beans.IntrospectionException;
import java.util.*;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private static UserDAOImpl manager;
    private SessionImpl session;

    UserDAOImpl() {
        this.session = SessionImpl.getInstance();
    }

    public static UserDAOImpl getInstance() {

        if(manager == null) {

            manager = new UserDAOImpl();
        }
        return manager;
    }

    // OK
    @Override
    public User addUser(String name, String password, String email) {
        User user = new User(name, password, email);
        logger.info("new user: " + user.toString());
        session.save(user);
        return user;
    }

    // OK
    @Override
    public User updateUser(String oldUsername, String name, String password, String email) throws IntrospectionException {
        User user = (User) this.session.getByName(User.class, oldUsername);
        //String id = user.getId();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        this.session.update(user);
        return user;
    }

    // OK
    @Override
    public User getUserById(String id) {
        User user = (User) this.session.getById(User.class, id);
        if (user.getName() == null) return null;
        logger.info("user by id: " + user.toString());
        return user;
    }

    // OK
    @Override
    public User getUserByName(String name) {
        User user = (User) this.session.getByName(User.class, name);
        if (user.getName() == null) return null;
        logger.info("user by name: " + user.toString());

        return user;
    }

    // OK
    @Override
    public User getUserByEmail(String email) {
        List<User> userList = this.getAllUsers();
        for (User user : userList) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    // OK
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<>();
        session.getAll(User.class).forEach(u -> userList.add((User) u));
        return userList;
    }

    // OK
    @Override
    public List<User> getCoinRanking() {
        List<User> userList = this.session.getAll(User.class);

        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getCoins() >= o2.getCoins())
                    return 1;
                if (o1.getCoins() < o2.getCoins())
                    return -1;
                return 0;
            }
        });
        Collections.reverse(userList);
        return userList;
    }

    // OK
    @Override
    public void deleteUser(String name) throws IntrospectionException {
        User u = (User) this.session.getByName(User.class, name);
        logger.info("user to delete: " + u.toString());
        session.delete(u);
    }
}