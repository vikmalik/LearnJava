package com.learnjava.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sample2 {
	private List<String> myList = Collections.synchronizedList(new ArrayList<String>());


	private class A implements Runnable{
		private String threadName;

		public A(){

		}

		public A(String threadName){
			this.threadName = threadName;
		}

		@Override
		public void run() {
			while(true){
				addEntry(threadName);
				try {
					Thread.sleep(0,10);
				} catch (InterruptedException e) {
					break;
				}
			}

		}

	}

	private int counter = 0;

	private void addEntry(String threadName){
		synchronized (myList) {	
			myList.add(new String("Hello" + counter++));
		}
		//System.out.println(threadName +" : Counter = " + counter);
	}

	private void doMain(String[] args){
		ExecutorService service = Executors.newCachedThreadPool();

		service.submit(new A("1"));
		service.submit(new A("2"));
		service.submit(new A("3"));
		service.submit(new A("4"));
		service.submit(new A("5"));
		service.submit(new A("6"));
		service.submit(new A("7"));

		try {
			Thread.sleep(1000);
			System.out.println("Close thread");
		} catch (InterruptedException e) {
		}

		service.shutdownNow();

		while(!service.isTerminated()){}

		int count = 0;
		for(String str : myList){
			if(!str.equals("Hello"+count++)){
				System.out.println(str);
			}
		}

		System.out.println("\n\n\n\nComplete List");

		for(String str : myList){
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		new Sample2().doMain(args);
	}
}
