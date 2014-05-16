package com.learnjava.concurrency;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaximumFinder extends RecursiveTask<Integer> {


	private static final long serialVersionUID = 3871892122748272778L;

	private static final int SEQUENTIAL_THRESHOLD = 5;

	private final int[] data;
	private final int start;
	private final int end;

	public MaximumFinder(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	public MaximumFinder(int[] data) {
		this(data, 0, data.length);
	}

	@Override
	protected Integer compute() {
		final int length = end - start;
		if (length < SEQUENTIAL_THRESHOLD) {
			return computeDirectly();
		}
		final int split = length / 2;
		final MaximumFinder left = new MaximumFinder(data, start, start + split);
		left.fork();
		final MaximumFinder right = new MaximumFinder(data, start + split, end);
		return Math.max(right.compute(), left.join());
	}

	private Integer computeDirectly() {
		//System.out.println(Thread.currentThread() + " computing: " + start	+ " to " + end);
		int max = Integer.MIN_VALUE;
		for (int i = start; i < end; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		return max;
	}

	private int getMax(){
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// create a random data set
		final int[] data = new int[1000000];
		final Random random = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(1000000);
		}

		final MaximumFinder finder = new MaximumFinder(data);
		// submit the task to the pool
		final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());		
		long initTime = System.currentTimeMillis();
		int max = pool.invoke(finder);
		long finalTime = System.currentTimeMillis();
		System.out.println("Max value =" + max + " in " + (finalTime - initTime) + "ms");

		long _initTime = System.currentTimeMillis();
		long _max = finder.getMax();
		long _finalTime = System.currentTimeMillis();
		System.out.println("Max value =" + _max + " in " + (_finalTime - _initTime) + "ms");


	}
}