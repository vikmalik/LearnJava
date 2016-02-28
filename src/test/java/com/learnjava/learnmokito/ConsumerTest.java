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
import org.junit.experimental.theories.Theory;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author vikmalik
 */
public class ConsumerTest {

    private Producer producer;

    public ConsumerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        producer = mock(Producer.class);
    }

    @After
    public void tearDown() {
        producer = null;
    }

    /**
     * Test of consumerMethod method, of class Consumer.
     */
    @Test
    public void testConsumerMethod_producerNullCheck() {
        System.out.println("testConsumerMethod_producerNullCheck");
        Producer localProducer = null;
        Consumer instance = new Consumer();
        int expResult = -1;
        int result = instance.consumerMethod(localProducer);
        assertEquals(expResult, result);
    }

    
    
    @Test
    public void testConsumerMethod_withMock_noRealMethodCall() {
        System.out.println("testConsumerMethod_withMock");
        Consumer instance = new Consumer();
        int expResult = -3;
        int result = instance.consumerMethod(producer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConsumerMethod_success() {
        System.out.println("testConsumerMethod_success");
        Producer localProducer = new Producer("test");
        Consumer instance = new Consumer();
        int expResult = 0;
        int result = instance.consumerMethod(localProducer);
        assertEquals(expResult, result);
    }

    @Test
    public void testConsumerMethod_withMock_realMethodCall() {
        System.out.println("testConsumerMethod_withMock_realMethodCall");
        Consumer instance = new Consumer();
        int expResult = 0;
        //without thenCallRealMethod this test case was failing
        when(instance.consumerMethod(producer)).thenCallRealMethod();
        int result = instance.consumerMethod(producer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConsumerMethod_withMock_checkExceptionHandling() {
        System.out.println("testConsumerMethod_withMock_testException");
        Consumer instance = new Consumer();
        int expResult = -2;
        
        SQLException sqlException = mock(SQLException.class);
        when(instance.consumerMethod(producer)).thenThrow(sqlException);
        
        int result = instance.consumerMethod(producer);
        assertEquals(expResult, result);
    }
    
    @Test (expected=ArithmeticException.class)
    public void testConsumerMethod_withMock_checkUnexpectedExceptionHandling() {
        System.out.println("testConsumerMethod_withMock_checkUnexpectedExceptionHandling");
        Consumer instance = new Consumer();
        int expResult = -2;
        
        ArithmeticException arithmeticException = mock(ArithmeticException.class);
        when(instance.consumerMethod(producer)).thenThrow(arithmeticException);
        
        instance.consumerMethod(producer);
    }

}
