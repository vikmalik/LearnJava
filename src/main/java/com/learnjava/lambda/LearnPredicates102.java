/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 *
 * @author vikmalik
 */
public class LearnPredicates102 {

    private static class Employee {

        private String name;
        private String cityName;
        private int hraPercentage;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getHraPercentage() {
            return hraPercentage;
        }

        public void setHraPercentage(int hraPercentage) {
            this.hraPercentage = hraPercentage;
        }

        @Override
        public String toString() {
            return "Employee{" + "name=" + name + ", cityName=" + cityName + ", hraPercentage=" + hraPercentage + '}';
        }
    }

    public static final String[] cities = {"Delhi", "Gurgaon", "Faridabad", "Noida"};

    public static void main(String... args) {
        //Generate Data
        final List<Employee> empList = new ArrayList<>();
        final Random random = new Random();
        
        Runnable addEmployeesA = () -> addEmployees(empList, random, "Thread-A");
        Runnable addEmployeesB = () -> addEmployees(empList, random, "Thread-B");
        Runnable setHra = () -> setHRA(empList);
        
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(addEmployeesA);
        service.submit(setHra);
        service.submit(addEmployeesB);
        
        service.shutdown();
        
        //java.util.ConcurrentModificationException is thrown by this
        try{
            printErrorCases(empList);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        while(!service.isTerminated()){
        }
        
        System.out.println("Total Employees in list = " + empList.size());
        printErrorCases(empList);
    }
    
    private static void addEmployees(List<Employee> empList, Random random, String ThreadName){
        for (int i = 0; i < 1000; i++) {
            Employee e = new Employee(ThreadName + i);
            e.setCityName(cities[random.nextInt(cities.length)]);
            empList.add(e);
            System.out.println(e.getName() + " added successfully");
        }
    }
    
    private static void setHRA(List<Employee> empList){
        Consumer<Employee> hraConsumer = (emp) -> emp.setHraPercentage(30);

        //set 30% hra to people living in Gurgaon
        empList
                .stream()
                .filter((emp) -> emp.getCityName().equalsIgnoreCase(cities[1]))
                .forEach(hraConsumer);
    }
    
    private static void printErrorCases(List<Employee> empList){
        //print the list of employee left unassigned due to race condition
        empList
                .stream()
                .filter((emp) -> emp != null && emp.getCityName().equalsIgnoreCase(cities[1]))
                .filter((emp)-> emp != null && emp.getHraPercentage() != 30)
                .forEach(System.out::println);
    }
}
