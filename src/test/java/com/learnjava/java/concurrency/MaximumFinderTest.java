/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.java.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vikmalik
 */
public class MaximumFinderTest {

    private int[] data;
    private int max;

    public MaximumFinderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass");
    }

    @Before
    public void setUp() {
        System.out.println("setUp");
        data = new int[1000000];
        max = Integer.MIN_VALUE;
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(1000000);
            if(max < data[i]){
                max = data[i];
            }
        }
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    /**
     * Test of compute method, of class MaximumFinder.
     */
    @Test
    public void testCompute() {
        System.out.println("compute");
        MaximumFinder instance = new MaximumFinder(data);
        Integer expResult = max;
        final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());	
        Integer result = pool.invoke(instance);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Negative Test of compute method, of class MaximumFinder.
     */
    @Test
    public void negativeTestCompute() {
        System.out.println("compute");
        MaximumFinder instance = new MaximumFinder(data);
        Integer expResult = Integer.MIN_VALUE;
        final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());	
        Integer result = pool.invoke(instance);
        assertFalse(expResult == result);
    }

}