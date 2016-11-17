package com.learnjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Sample class will try to test,"Two synchronized methods (m1, m2) of a class(A) 
 * cannot be called by two separate threads (t1, t2) simultaneously"
 * 
 * @author vikmalik
 */
public class Sample5 {
    private static class A{
        public synchronized void m1() throws InterruptedException{
            System.out.println("m1 Started");
            Thread.sleep(1000l);
            System.out.println("m1 Ended");
        }
        
        public synchronized void m2() throws InterruptedException{
            System.out.println("m2 Started");
            Thread.sleep(1000l);
            System.out.println("m2 Ended");
        }
    }
    
    public static void main(String[] args) {
        final A a = new A();
        
        //T1 thread calls m1 method of object a when executed
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    a.m1();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sample5.class.getName()).log(Level.SEVERE, "InterruptedException in t1", ex);
                }
            }
            
        };
        
        //T2 thread calls m2 method of object a when executed
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    a.m2();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sample5.class.getName()).log(Level.SEVERE, "InterruptedException in t2", ex);
                }
            }
            
        };
        
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(t1);
        executorService.submit(t2);
        
        /**
         * Both the t1 and t2 are executing in parallel but due to synchronized keyword
         * only one of the method can be executed at a time. So output will be: 
         *      m1 Started
         *      m1 Ended
         *      m2 Started
         *      m2 Ended
         */
        executorService.shutdown();
    }
}


