package edu.upc.eetac.dsa.util;

import edu.upc.eetac.dsa.dao.impl.SessionImpl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class ObjectHelper {

    static final Logger logger = Logger.getLogger(ObjectHelper.class.getName());

    // OK
    public static String[] getFields(Object entity) {
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i = 0;
        for(Field f: fields) {
            sFields[i++] = f.getName();
        }
        return sFields;
    }

    // OK
    public static Object getter(Object entity, String fieldName) throws IntrospectionException {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(fieldName, entity.getClass());
            Object res = pd.getReadMethod().invoke(entity);
            return res;
        } catch (IntrospectionException e){
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    // OK
    public static void setter(Object entity, String fieldName, Object value) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(fieldName, entity.getClass());
            pd.getWriteMethod().invoke(entity, value);
        } catch (IntrospectionException e){
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
