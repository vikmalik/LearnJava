package com.learnjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sample4 {

    private static Thread sample4;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Sample4().doMain(args);

    }

    public void doMain(String[] args) {
        sample4 = Thread.currentThread();

        Runnable t1 = new Runnable() {
            public void run() {
                synchronized (sample4) {
                    A a = A.getInstance();
                    a.printIt();
                }

            }
        };

        Runnable t2 = new Runnable() {
            public void run() {
                A.getInstance().execute();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(t1);
        executorService.submit(t2);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        A.getInstance().printIt();
//		try {
//			Thread.currentThread().join();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    private static class A {

        private static A instance;

        private A() {
        }

        public static A getInstance() {
            if (instance == null) {
                synchronized (A.class) {
                    instance = instance == null ? new A() : instance;
                }
            }
            return instance;
        }

        public void execute() {
            synchronized (sample4) {
                System.out.println("Inside Sync" + System.currentTimeMillis());
                try {
                    sample4.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("executing it" + System.currentTimeMillis());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printIt() {

            System.out.println("I am printing it" + System.currentTimeMillis());
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done waiting" + System.currentTimeMillis());
        }
    }
}
