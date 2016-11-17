package com.learnjava.lambda;

import com.learnjava.bean.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vikmalik
 */
public class LearnLambda1 {

    //public static final Comparator<Person> BY_FIRST = Comparators.comparing(Person::getFirstName);
    //public static final Comparator<Person> BY_LAST = Comparators.comparing(Person::getLastName);
    //TODO: Need to check why it is not working
    //public static final Comparator<Person> BY_AGE = Comparators.comparing(Person::getAge);
    public static void main(String... args) {
        new LearnLambda1().doMain(args);
    }

    private void doMain(String... args) {
        //iterateUsingMethodReferences();
        //iterateUsingTypeInterface();
        transposeUsingLambda();
        aggregateUsingLambda();
        getMaximumUsingReducingLambda();

        List<Person> list = populateListData();
        //printListData(list);
        System.out.println("getPersonCount(): Number of person having age > 21 = " + getPersonCount(list, 21));
        
        System.out.println("getPersonCountMoreThanGivenAge(): Number of person having age > 21 = " + getPersonCountMoreThanGivenAge(list, 21));

        Person person = getPersonWithMaxAge(list);
        System.out.println("Person with Maximum Age :: " + person);
    }

    private ArrayList<Person> populateListData() {
        ArrayList<Person> list = new ArrayList<>();

        Random random = new Random();

        Person person;
        for (int i = 0; i < 100; i++) {
            person = new Person();
            person.setId(i);
            person.setFirstName("fname");
            person.setLastName("lname");
            person.setAge(random.nextInt(100));
            list.add(person);
        }
        return list;
    }

    private void printListData(List<Person> list) {
        if (list != null) {
            list.stream().forEach((person) -> {
                System.out.println(person.toString());
            });
        }
    }

    private int getPersonCount(List<Person> list, int age) {
        return list.stream().filter((it) -> it.getAge() >= age).mapToInt((it) -> 1).sum();
    }
    private long getPersonCountMoreThanGivenAge(List<Person> list, int age) {
        return list.stream().filter((it) -> it.getAge() >= age).count();
    }

    private Person getPersonWithMaxAge(List<Person> list) {
        return list.stream().reduce(list.get(0), (l, r) -> (r.getAge() <= l.getAge() ? l : r));
    }

    private void iterateUsingMethodReferences() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.forEach(System.out::println);
    }

    private void iterateUsingTypeInterface() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.forEach((value) -> System.out.println(value));
    }

    private void filterUsingLambda() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers
                .stream()
                .filter(value -> value % 2 == 0)
                .forEach((value) -> System.out.println(value));
    }

    private void transposeUsingLambda() {
        List<String> teamMembers = Arrays.asList("vikas", "chandan", "shweta", "vinod");
        System.out.println("User Parallel Stream: ");
        teamMembers.parallelStream().map(value -> value.toUpperCase()).forEach(System.out::println);

        System.out.println("User Sequential Stream: ");
        teamMembers.stream().map(value -> value.toUpperCase()).forEach(System.out::println);
    }

    private void aggregateUsingLambda() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers
                .stream()
                .filter(value -> value % 2 == 0)
                .mapToInt(value -> value)
                .sum();
        System.out.println("Sum of even numbers = " + sum);
    }

    private void getMaximumUsingReducingLambda() {
        List<Integer> numbers = Arrays.asList(1, 21, 3, 43, 51, 62, 17, 81, 19, 10);
        int maxValue = numbers
                .stream()
                .reduce(numbers.get(0), (l, r) -> (l <= r ? r : l));
        System.out.println("max using lambda  = " + maxValue);
        
        //equalent code 
        int result = numbers.get(0);
        for (int element : numbers)
            result = result <= element ? element : result;
        System.out.println("max without using lambda = " + maxValue);
    }
}
