/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.java.concurrency;

import com.learnjava.concurrency.ParallelMergeSort;
import java.util.Random;
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
public class ParallelMergeSortTest {
    
    private Comparable[] data;
    
    public ParallelMergeSortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        data = new Comparable[100];
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100000);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sort method, of class ParallelMergeSort.
     */
    @Test
    public void testSort_ComparableArr() {
        System.out.println("sort");
        ParallelMergeSort.sort(data);
        
        for (int i = 0; i < data.length - 1; i++) {
            if(data[i].compareTo(data [i+1]) > 0){
                fail("Data is not sorted");
            }
        }
    }
}