package com.learnjava.java.concurrency;

public class Sample1 {

    private static class Sample2 extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this.getName() + " running");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Sample1_1 extends Thread {

        @Override
        public void run() {
            try {
                sleep(2000);
                System.out.println("Notify All thread waiting for " + Thread.currentThread().getName());
                synchronized (this) {
                    notifyAll();
                }
                sleep(2000);
                System.out.println("Finish " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Sample2 sample1 = new Sample2();
        sample1.start();
        System.out.println("Current Thread = " + Thread.currentThread().getName());

        Sample1_1 sample1_1 = new Sample1_1();
        sample1_1.start();

        try {
            synchronized (sample1_1) {
                sample1_1.wait();
            }
            sample1.interrupt();
            sample1_1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("We reached End of Main Thread");

        //		Sample2 sample2 = new Sample2();
        //		sample2.setName("Thread 2");
        //		try {
        //			sample2.join();
        //		} catch (InterruptedException e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}
        //		sample2.start();
        //		NewThread ob1 = new NewThread("One");
        //		NewThread ob2 = new NewThread("Two");
        //		NewThread ob3 = new NewThread("Three");
        //		System.out.println("Thread One is alive: "+ ob1.t.isAlive());
        //		System.out.println("Thread Two is alive: "+ ob2.t.isAlive());
        //		System.out.println("Thread Three is alive: "+ ob3.t.isAlive());
        //		// wait for threads to finish
        //		try {
        //			System.out.println("Waiting for threads to finish.");
        //			ob1.t.join();
        //			ob2.t.join();
        //			ob3.t.join();
        //		} catch (InterruptedException e) {
        //			System.out.println("Main thread Interrupted");
        //		}
        //		System.out.println("Thread One is alive: "+ ob1.t.isAlive());
        //		System.out.println("Thread Two is alive: "+ ob2.t.isAlive());
        //		System.out.println("Thread Three is alive: "+ ob3.t.isAlive());
        //		System.out.println("Main thread exiting.");
    }

    private static class NewThread implements Runnable {

        String name; // name of thread
        Thread t;

        NewThread(String threadname) {
            name = threadname;
            t = new Thread(this, name);
            System.out.println("New thread: " + t);
            t.start(); // Start the thread
        }
        // This is the entry point for thread.

        public void run() {
            try {
                for (int i = 50; i > 0; i--) {
                    System.out.println(name + ": " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(name + " interrupted.");
            }
            System.out.println(name + " exiting.");
        }
    }

}
