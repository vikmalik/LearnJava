package com.learnjava.security;

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
public class EncriptAESUtilTest {
    
    public EncriptAESUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encryptText method, of class EncriptAESUtil.
     */
    @Test
    public void testEncryptText() {
        System.out.println("encryptText");
        String queryString = "Hello AES";
        EncriptAESUtil instance = new EncriptAESUtil();
        byte[] expResult = null;
        byte[] result = instance.encryptText(queryString);
        System.out.println("encrypted text = " + result);
        assertArrayEquals(expResult, result);
    }

    
}
