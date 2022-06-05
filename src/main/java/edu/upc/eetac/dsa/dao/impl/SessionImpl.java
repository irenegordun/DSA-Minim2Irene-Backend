package edu.upc.eetac.dsa.dao.impl;

import edu.upc.eetac.dsa.dao.Session;
import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.beans.IntrospectionException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class SessionImpl<E> implements Session<E> {

    private final Connection conn;
    private static SessionImpl instance;
    static final Logger logger = Logger.getLogger(SessionImpl.class.getName());


    SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public static SessionImpl getInstance(){
        if(instance==null){
            Connection conn = FactorySession.getConnection();
            instance = new SessionImpl(conn);
        }
        return instance;
    }

    // OK
    @Override
    public void save(E entity) {
        String query = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);

            int i = 0;
            for (String f: ObjectHelper.getFields(entity)) {
                pstm.setObject(++i, ObjectHelper.getter(entity,f));
            }
            pstm.executeQuery();

        } catch (SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
    }

    // OK
    @Override
    public E getById(Class theClass, String id) {
        String query = QueryHelper.createQuerySELECTById(theClass, id);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            Object entity = theClass.newInstance();

            while(rs.next()) {
                for (int i = 1; i<rsmd.getColumnCount() + 1; i++) {
                    ObjectHelper.setter(entity,rsmd.getColumnName(i),rs.getObject(i));
                }
            }

            /*if(ObjectHelper.getter(entity,"id") == null) {
                return null;
            }*/

            return (E) entity;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    // OK
    @Override
    public E getByName(Class theClass, String name) {
        String query = QueryHelper.createQuerySELECTByName(theClass, name);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            Object entity = theClass.newInstance();

            while(rs.next()) {
                for (int i = 1; i<rsmd.getColumnCount() + 1; i++) {
                    ObjectHelper.setter(entity,rsmd.getColumnName(i),rs.getObject(i));
                    //logger.info("i = " + i + ", nom columna: " + rsmd.getColumnName(i) + ", value: " + rs.getObject(i));
                }
            }

            /*if((ObjectHelper.getter(entity,"name") == null)) {
                logger.info("entra a null");
                return null;
            }*/

            return (E) entity;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    // OK
    @Override
    public List<E> getAll(Class theClass) {
        String query = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm = null;
        List<E> res = new LinkedList<E>();

        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                Object entity = theClass.newInstance();
                for (int i = 1; i<rsmd.getColumnCount() + 1; i++) {
                    ObjectHelper.setter(entity,rsmd.getColumnName(i),rs.getObject(i));
                }
                res.add((E) entity);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    // OK
    @Override
    public void update(E entity) throws IntrospectionException {
        String query = QueryHelper.createQueryUPDATE(entity);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);

            int i = 1;
            for (String f: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++,ObjectHelper.getter(entity, f));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // OK
    @Override
    public void delete(E entity) throws IntrospectionException {
        String query = QueryHelper.createQueryDELETE(entity);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
