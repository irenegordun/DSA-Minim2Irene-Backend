package edu.upc.eetac.dsa.dao;

import edu.upc.eetac.dsa.models.User;

import java.beans.IntrospectionException;
import java.util.List;

public interface UserDAO {
    public User addUser(String name, String password, String email);
    public User updateUser(String oldUsername, String name, String password, String email) throws IntrospectionException;
    public User getUserById(String id);
    public User getUserByName(String name);
    public User getUserByEmail(String email);
    public List<User> getAllUsers();
    public List<User> getCoinRanking();
    public void deleteUser(String name) throws IntrospectionException;
}
