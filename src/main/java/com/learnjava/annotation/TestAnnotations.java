package com.learnjava.annotation;

@Author (name = "Vikas")
@Version (number = 1.1)
public class TestAnnotations {

	@Author (name = "Vikas")
	@Version (number = 1.0)
	public static void main(String[] args) {
		new TestAnnotations().printNumber();
	}
	
	@Author (name = "Sachin")
	@Version (number = 1.1)
	private void printNumber(){
		System.out.println("TestAnnotations.printNumber()");
	}
}
