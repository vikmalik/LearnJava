/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author vikmalik
 */
public class LearnPradicates101 {

    private static class Student {

        private String name;
        private float grades;
        private float fee = 10000;
        private int discount;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public float getGrades() {
            return grades;
        }

        public void setGrades(float grades) {
            this.grades = grades;
        }

        public float getFee() {
            return fee - (fee * discount/100);
        }

        public int getDiscount() {
            return discount;
        }
        
        public void setDiscount(int discount){
            this.discount = discount;
        }

        public void setDiscount(Predicate<Student> predicate, Consumer<Student> consumer) {
            //Use the predicate to decide when to update the discount.
            if (predicate.test(this)) {
                //Use the consumer to update the discount value.
                consumer.accept(this);
            }

        }

        @Override
        public String toString() {
            return "Student{" + "name=" + name + ", grades=" + grades + ", fee=" + fee + ", discount=" + discount + '}';
        }
    }

    public static void main(String... args) {
        new LearnPradicates101().doMain(args);
    }

    private void doMain(String... args) {
        Student s1 = new Student("Vikas");
        Student s2 = new Student("Sachin");

        s1.setGrades(8);
        s2.setGrades(9.6f);
        
        Predicate<Student> gradePredicate8 = (student) -> student.grades < 8;
        Predicate<Student> gradePredicate9_5 = (student) -> student.grades >= 9.5;
        Predicate<Student> gradesPredicate8to9_5 = gradePredicate8.negate().and(gradePredicate9_5.negate());

        s1.setDiscount(gradesPredicate8to9_5, student -> student.setDiscount(20));
        s2.setDiscount(gradePredicate9_5, student -> student.setDiscount(30));
        
        System.out.println(s1);
        System.out.println(s2);
    }
}
