package edu.upc.eetac.dsa.util;

import java.beans.IntrospectionException;

public class QueryHelper {

    // OK
    public static String createQueryINSERT(Object entity) {
        StringBuffer sb = new StringBuffer("INSERT INTO ").
                append(entity.getClass().getSimpleName()).
                append(" (");

        for (String f: ObjectHelper.getFields(entity)) {
            sb.append(f).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1); //comma and space
        sb.deleteCharAt(sb.length() - 1);

        sb.append(") VALUES (");
        for (String f: ObjectHelper.getFields(entity)) {
            sb.append("?, ");
        }
        sb.deleteCharAt(sb.length() - 1); //comma and space
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    // OK
    public static String createQueryUPDATE(Object entity) throws IntrospectionException {
        StringBuffer sb = new StringBuffer("");
        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).
                append(" SET ");

        String id = ("" + ObjectHelper.getter(entity, "id"));
        String[] fields = ObjectHelper.getFields(entity);
        for (String f: fields) {
            sb.append(f).append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = '").append(id).append("'");
        return sb.toString();
    }

    // OK
    public static String createQuerySELECTById(Class theClass, String id) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE id = '").append(id).append("'");
        return sb.toString();
    }

    // OK
    public static String createQuerySELECTByName(Class theClass, String name) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE name = '").append(name).append("'");
        return sb.toString();
    }

    // OK
    public static String createQuerySELECTAll(Class theClass) {
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        return sb.toString();
    }

    // OK
    public static String createQueryDELETE(Object entity) throws IntrospectionException {
        StringBuffer sb = new StringBuffer("");
        String id = (ObjectHelper.getter(entity, "id").toString());
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName()).
                append(" WHERE id='").append(id).append("'");
        return sb.toString();
    }
}
