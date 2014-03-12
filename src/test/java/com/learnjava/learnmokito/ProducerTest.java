/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.learnmokito;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author vikmalik
 */
public class ProducerTest {
    
    SQLException sqlException;
    
    public ProducerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sqlException = mock(SQLException.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of produceMethod method, of class Producer.
     * @throws java.lang.Exception
     */
    @Test
    public void testProduceMethod() throws Exception {
        System.out.println("produceMethod");
        Producer instance = new Producer("test");
        String expResult = "test";
        String result = instance.produceMethod();
        assertEquals(expResult, result);
    }
    
}
