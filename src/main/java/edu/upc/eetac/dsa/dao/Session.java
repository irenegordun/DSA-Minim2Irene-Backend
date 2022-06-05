package edu.upc.eetac.dsa.dao;

import java.beans.IntrospectionException;
import java.util.List;

public interface Session<E> {
    void save(E entity);
    E getById(Class theClass, String id);
    E getByName(Class theClass, String name);
    List<E> getAll(Class theClass);
    void update(E entity) throws IntrospectionException;
    void delete(E entity) throws IntrospectionException;
    void close();
}
