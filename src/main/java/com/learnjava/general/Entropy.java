package com.learnjava.general;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * This is weird hack, If you change the internal cache of Integer class then
 * by typecasting int to Integer will provide same values as changed by you earlier.
 * 
 */
public class Entropy {

    public static void main(String[] args) throws Exception {
        // Extract the IntegerCache through reflection
        Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);

        System.out.println("Cache Lenght = " + cache.length);
        // Rewrite the Integer cache
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Integer(
                    new Random().nextInt(cache.length));
        }
        

        // Prove randomness
        for (int i = 0; i < 20; i++) {
            System.out.println((Integer) i);
        }
    }
}
