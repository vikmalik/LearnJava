package com.research;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author vikmalik
 */
public class MultiIndexList1Test {
    private static List<String> indexList = null;
    private MultiIndexList1 instance;

    private static MyBean[] myBeans;

    public MultiIndexList1Test() {
    }

    @BeforeClass
    public static void setUpClass() {
	indexList = new ArrayList<>();
	indexList.add("id");
	indexList.add("jid");

	myBeans = new MyBean[1000];
	for (int i = 0; i < 1000; i++) {
	    myBeans[i] = new MyBean("" + i, "" + i);
	}
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
	instance = new MultiIndexList1("id", "jid");
    }

    @After
    public void tearDown() {
	instance = null;
    }

    /**
     * Test of add method, of class MultiIndexList1.
     */
    @Test
    public void testAdd() {
	System.out.println("testAdd()");
	for (MyBean item : myBeans) {
	    boolean expResult = true;
	    boolean result = instance.add(item);
	    assertEquals(expResult, result);
	}
    }

    /**
     * Test of add method, of class MultiIndexList1.
     */
    @Test
    public void testAddNegative() {
	System.out.println("testAddNegative()");
	String item = "1";
	boolean expResult = false;
	boolean result = instance.add(item);
	assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class MultiIndexList1.
     */
    @Test
    public void testGet_int() {
	System.out.println("testGet_int()");

	for (int i = 0; i < 100; i++) {
	    instance.add(myBeans[i]);
	}

	int index = 5;
	Object expResult = myBeans[5];
	Object result = instance.get(index);
	assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class MultiIndexList1.
     */
    @Test
    public void testGet_String_Object() {
	System.out.println("testGet_String_Object()");

	for (int i = 0; i < 100; i++) {
	    instance.add(myBeans[i]);
	}

	String keyName = "id";
	Object keyValue = "5";
	Object expResult = myBeans[5];
	Object result = instance.get(keyName, keyValue);
	assertEquals(expResult, result);
    }

    @Test
    public void testPerformance_Integer() {
	System.out.println("testPerformance_Integer()");
	for (int i = 100; i <= 100000; i *= 10) {
	    setUp();
	    performanceTest(i);
	    tearDown();
	}
	assertEquals(true, true);
    }

    private void performanceTest(int limit) {
	MyBean bean;

	long startTime = System.currentTimeMillis();
	for (int i = 0; i < limit; i++) {
	    bean = new MyBean("" + i, "" + i);
	    instance.add(bean);
	}
	long finishTime = System.currentTimeMillis();
	System.out.println("Total Data Insertion Time = "
		+ (finishTime - startTime) + "ms for List size = "
		+ instance.size());

	startTime = System.currentTimeMillis();
	int size = instance.size();
	for (int i = 0; i < size; i++) {
	    instance.get("id", "" + i);
	}
	finishTime = System.currentTimeMillis();

	System.out.println("Total List Traversal Time = "
		+ (finishTime - startTime) + "ms for List size = "
		+ instance.size());
    }
}
