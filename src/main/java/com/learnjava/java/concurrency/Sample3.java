package com.learnjava.java.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Sample3 {

	private interface MyList{
		public void addMyEntry(String entry);
		public List<String> getMyList();
	}

	private enum TestEnumSingleton implements MyList{

		myInstance;

		private List<String> myList = Collections.synchronizedList(new ArrayList<String>()); 

		TestEnumSingleton(){

		}

		public void addMyEntry(String entry){
			myList.add(entry);
		}

		public List<String> getMyList(){
			return myList;
		}
	}

	private static class TestOneSingleton implements MyList{

		private static TestOneSingleton instance;

		private List<String> myList = Collections.synchronizedList(new ArrayList<String>());

		private TestOneSingleton(){

		}

		public static TestOneSingleton getInstance(){

			synchronized (TestOneSingleton.class) {
				if(instance == null){

					instance = new TestOneSingleton();
				}
			}

			return instance;
		}

		public void addMyEntry(String entry){
			myList.add(entry);
		}

		public List<String> getMyList(){
			return myList;
		}
	}


	private class MyTask implements Runnable{
		private String threadName;
		private char singletonType;

		public MyTask(){

		}

		public MyTask(String threadName, char singletonType){
			this.threadName = threadName;
			this.singletonType = singletonType;
		}

		@Override
		public void run() {
			int counter = 0;

			while(true){
				//Add new string
				switch(singletonType){
				case '1':
					TestOneSingleton.getInstance().addMyEntry(threadName + counter++);
					break;
				default:
					TestEnumSingleton.myInstance.addMyEntry(threadName + counter++);
				}

				try {
					Thread.sleep(0,5);
				} catch (InterruptedException e) {
					break;
				}
			}

		}
	}

	public static void main(String[] args) {
		new Sample3().doMain(args);
	}

	private void doMain(String[] args){
		ExecutorService service = Executors.newCachedThreadPool();

		char singletonType = '0';

		if(args.length > 0){
			if(args[0].equals("1")){
				singletonType = '1'; 
			}
		}

		System.out.println("Singleton Type = " + singletonType);

		service.submit(new MyTask("1 ", singletonType));
		service.submit(new MyTask("2 ", singletonType));
		service.submit(new MyTask("3 ", singletonType));
		service.submit(new MyTask("4 ", singletonType));
		service.submit(new MyTask("5 ", singletonType));
		service.submit(new MyTask("6 ", singletonType));
		service.submit(new MyTask("7 ", singletonType));

		try {
			Thread.sleep(1000);
			System.out.println("Close thread");
		} catch (InterruptedException e) {
		}

		service.shutdownNow();

		while(!service.isTerminated()){
			//do nothing till all threads stops
		}

		MyList t;

		switch(singletonType){
		case '1':
			t = TestOneSingleton.getInstance();
			break;
		default:
			t = TestEnumSingleton.myInstance;
		}

		System.out.println("Size of list = " + t.getMyList().size());

		HashMap<String, List<Integer>> entriesPerThread = new HashMap<String, List<Integer>>();

		String key;

		for(String str : t.getMyList()){
			key = ""+ str.charAt(0);

			List<Integer> list = entriesPerThread.get(key) != null ? entriesPerThread.get(key) : new ArrayList<Integer>();

			try{
				list.add(new Integer(str.substring(1).trim()));
			}catch(NullPointerException e){
				System.err.println("list = " + list);
				System.err.println("Exception occured - Thread = " + key + " , String = " + str.substring(1).trim());
				e.printStackTrace();
			}

			entriesPerThread.put(key, list);
		}

		for(String entryKey: entriesPerThread.keySet()){
			System.out.println("Testing Thread = "+ entryKey);
			List<Integer> list = entriesPerThread.get(entryKey);
			int counter = 0;
			boolean isOK = true;
			for (int i = 0; i < list.size(); i++) {
				if(counter != list.get(i)){
					isOK = false;
					System.out.println("Entry Missing - Thread = " + entryKey + " , counter = "+ counter + " , list value = " + list.get(i));
					counter = list.get(i);
					//break;
				}
				counter++;
			}

			if(! isOK){
				System.out.println("Failed to add Entry for Thread = "+ entryKey);
			}
		}

		System.out.println("Sample Executed Successfully");
	}
}
