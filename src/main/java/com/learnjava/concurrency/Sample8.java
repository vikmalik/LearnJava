package com.learnjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Sample class will try to test,"Two  methods (m1, m2) of a class(A) having 
 * synchronized blocks (synchronized on separate objects) can only be called by two 
 * separate threads (t1, t2) simultaneously without blocking each other. If any other
 * thread (t3) try to execute methods (eg. m1) then it has to wait till earlier thread
 * is finished"
 * 
 * @author vikmalik
 */
public class Sample8 {
    private static class A{
        final Object lockM1 = new ReentrantLock();
        final Object lockM2 = new ReentrantLock();
        
        public void m1() throws InterruptedException{
            System.out.println("m1 Started - " + Thread.currentThread() );
            synchronized(lockM1){
                Thread.sleep(1000l);
            }
            System.out.println("m1 Ended - " + Thread.currentThread());
        }
        
        public void m2() throws InterruptedException{
            System.out.println("m2 Started - " + Thread.currentThread());
            synchronized(lockM2){
                Thread.sleep(100l);
            }
            System.out.println("m2 Ended - " + Thread.currentThread());
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
                    Logger.getLogger(Sample8.class.getName()).log(Level.SEVERE, "InterruptedException in t1", ex);
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
                    Logger.getLogger(Sample8.class.getName()).log(Level.SEVERE, "InterruptedException in t2", ex);
                }
            }
            
        };
        
        //T3 thread calls m1 method of object a when executed
        Thread t3 = new Thread(){
            @Override
            public void run() {
                try {
                    a.m1();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sample8.class.getName()).log(Level.SEVERE, "InterruptedException in t3", ex);
                }
            }
            
        };
        
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);
        
        /**
         * All t1, t2 and t3 are executing in parallel, but only t1 and t2 could 
         * aquire lock on methods m1 and m2 respectively and t3 has to wait till
         * t1 finishes the execution.
         * So output will be: 
         *      m1 Started - Thread[pool-1-thread-1,5,main]
         *      m2 Started - Thread[pool-1-thread-2,5,main]
         *      m1 Started - Thread[pool-1-thread-3,5,main]
         *      m2 Ended - Thread[pool-1-thread-2,5,main]
         *      m1 Ended - Thread[pool-1-thread-1,5,main]
         *      m1 Ended - Thread[pool-1-thread-3,5,main]
         */
        executorService.shutdown();
    }
}


