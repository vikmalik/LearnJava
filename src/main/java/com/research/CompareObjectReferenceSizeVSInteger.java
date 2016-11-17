/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.research;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author vikmalik
 */
public class CompareObjectReferenceSizeVSInteger {

    public static void main(String... args) {
	CompareObjectReferenceSizeVSInteger instance = new CompareObjectReferenceSizeVSInteger();
	instance.methodUseObjectReference();
    }

    private void methodUseObjectReference() {
        TestBean bean = new TestBean(1, 1, "Test", "Test You", "Test Me");
        
        ArrayList<TestBean> list = new ArrayList<>();
        int counter = 0;
        do{
            for(int i=0; i<10000; i++){
                list.add(bean);
            }            
            try {
                synchronized(this){
                    this.wait(5*1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CompareObjectReferenceSizeVSInteger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(counter++ < 10);
    }

    private void methodUseInteger() {
        TestBean bean = new TestBean(1, 1, "Test", "Test You", "Test Me");
        
        ArrayList<Integer> list = new ArrayList<>();
        int counter = 0;
        do{
            for(int i=0; i<10000; i++){
                list.add(i);
            }            
            try {
                synchronized(this){
                    this.wait(5*1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CompareObjectReferenceSizeVSInteger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(counter++ < 10);
    }

    private static class TestBean {
	int id;
	long lid;
	String testName;
	String propertyName;
	String PropertyValue;

	public TestBean(int id, long lid, String testName, String propertyName,
		String PropertyValue) {
	    this.id = id;
	    this.lid = lid;
	    this.testName = testName;
	    this.propertyName = propertyName;
	    this.PropertyValue = PropertyValue;
	}

    }
}
