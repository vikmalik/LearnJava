package com.research;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO Need to fix the logic if two objects with same Index index value added
 *
 * @author vikmalik
 * @param <V>
 */
public class MultiIndexList1<V> extends ArrayList {

    private static final Logger l = Logger.getLogger(MultiIndexList.class.getName());

    private final HashMap<String, HashMap<?, Integer>> indexMaps = new HashMap<>();
    private final HashMap<String, String> indexMethodsMap;

    private static final String METHOD_PREFIX = "get";

    /**
     * Parameterized Constructor
     *
     * @param indexField
     */
    public MultiIndexList1(String... indexField) {

        if (indexField != null) {
            indexMethodsMap = new HashMap<>(indexField.length);
            StringBuilder methodName;
            for (String inderxFieldName : indexField) {
                methodName = new StringBuilder(METHOD_PREFIX);
                char[] fieldNameCharArray = inderxFieldName.toCharArray();
                fieldNameCharArray[0] = Character.toUpperCase(fieldNameCharArray[0]);
                methodName.append(fieldNameCharArray);
                l.log(Level.FINEST, "Method Name = {0}", methodName);
                indexMethodsMap.put(inderxFieldName, methodName.toString());
                indexMaps.put(inderxFieldName, new HashMap<Object, Integer>());
            }
        } else {
            indexMethodsMap = null;
        }
    }

    @Override
    public boolean add(Object item) {
        boolean isAdded = validateAndAddObject(item);
        return isAdded;
    }

    public Object get(String keyName, Object keyValue) {
        Object returnValue = null;
        HashMap<?, Integer> indexMap = indexMaps.get(keyName);
        if (indexMap != null) {
            Integer index = indexMap.get(keyValue);
            if(index != null){
                returnValue = super.get(index);
            }
        }
        return returnValue;
    }

    /**
     * At least one of the index method should be present in inserted object
     *
     * @param item
     * @return
     */
    private boolean validateAndAddObject(Object item) {
        boolean isValid = false;
        int itemIndex = -1;
        Method[] methods = item.getClass().getMethods();

        for (String indexName : indexMethodsMap.keySet()) {
            String indexMethodName = indexMethodsMap.get(indexName);
            for (Method method : methods) {
                if (indexMethodName.equals(method.getName())) {
                    if (!isValid) {
                        super.add(item);
                        itemIndex = super.indexOf(item);
                        isValid = true;
                    }
                    addIndex(itemIndex, indexName, method);
                }
            }
        }
        return isValid;
    }

    /**
     * This method create index for the object
     *
     * @param dataIndex
     * @param indexName
     * @param method
     */
    private void addIndex(int dataIndex, String indexName, Method method) {
        Object item = super.get(dataIndex);
        HashMap indexMap = indexMaps.get(indexName);
        try {
            Object key = method.invoke(item);
            indexMap.put(key, dataIndex);
        } catch (IllegalAccessException ex) {
            l.log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | InvocationTargetException e) {
            l.log(Level.SEVERE, null, e);
        }
    }
}