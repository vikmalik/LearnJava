package com.learnjava.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author vikmalik
 */
public class LearnHashcodeWithCollection {
    public static void main(String[] args){
        testSets();
        testLists();
        testMaps();
        testMapsWithAClass();
        testMapsWithBClass();
        testMapsWithCClass();
        testMapsWithDClass();
    }
    
    private static void testSets(){
        Set<String> myset = new HashSet<>();
        
        String a1 = "abc";
        String a2 = new String("abc");
        String a3 = new String("abc");
        String a4 = new String(a3);
        
        myset.add(a1);
        myset.add(a2);
        myset.add(a3);
        myset.add(a4);
        
        System.out.println("Size of myset = " + myset.size());
    }
    
    private static void testLists(){
        List<String> myList = new ArrayList<>();
        
        String a1 = "abc";
        String a2 = new String("abc");
        String a3 = new String("abc");
        String a4 = new String(a3);
        
        myList.add(a1);
        myList.add(a2);
        myList.add(a3);
        myList.add(a4);
        
        System.out.println("Size of myList = " + myList.size());
    }
    
    
    private static void testMaps(){
        Map<String, String> myMap = new HashMap<>();
        
        String a1 = "abc";
        String a2 = new String("abc");
        String a3 = new String("abc");
        String a4 = new String(a3);
        
        myMap.put(a1, a1);
        myMap.put(a2, a2);
        myMap.put(a3, a3);
        myMap.put(a4, a4);
        
        System.out.println("Size of myMap = " + myMap.size());
    }
    
    private static void testMapsWithAClass(){
        Map<A, A> myMap = new HashMap<>();
        
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");
        String s4 = new String(s3);
        
        A a1 = new A(s1);
        A a2 = new A(s2);
        A a3 = new A(s3);
        A a4 = new A(s4);
     
        myMap.put(a1, a1);
        myMap.put(a2, a2);
        myMap.put(a3, a3);
        myMap.put(a4, a4);
        
        System.out.println("Size of myMap with A class = " + myMap.size());
    }
    
    private static void testMapsWithBClass(){
        Map<B, B> myMap = new HashMap<>();
        
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");
        String s4 = new String(s3);
        
        B b1 = new B(s1);
        B b2 = new B(s2);
        B b3 = new B(s3);
        B b4 = new B(s4);
     
        myMap.put(b1, b1);
        myMap.put(b2, b2);
        myMap.put(b3, b3);
        myMap.put(b4, b4);
        
        System.out.println("Size of myMap with B class (only hashCode implementation) = " + myMap.size());
    }
    
    private static void testMapsWithCClass(){
        Map<C, C> myMap = new HashMap<>();
        
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");
        String s4 = new String(s3);
        
        C b1 = new C(s1);
        C b2 = new C(s2);
        C b3 = new C(s3);
        C b4 = new C(s4);
     
        myMap.put(b1, b1);
        myMap.put(b2, b2);
        myMap.put(b3, b3);
        myMap.put(b4, b4);
        
        System.out.println("Size of myMap with C class (only hashCode & equals implementation) = " + myMap.size());
    }
    
    private static void testMapsWithDClass(){
        Map<D, D> myMap = new HashMap<>();
        
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");
        String s4 = new String(s3);
        
        D b1 = new D(s1);
        D b2 = new D(s2);
        D b3 = new D(s3);
        D b4 = new D(s4);
     
        myMap.put(b1, b1);
        myMap.put(b2, b2);
        myMap.put(b3, b3);
        myMap.put(b4, b4);
        
        System.out.println("Size of myMap with C class (only equals implementation) = " + myMap.size());
    }
    
    
    private static class A{
        String a;
        public A(String a){
            this.a = a;
        }
    }
    
    private static class B{
        String a;
        public B(String a){
            this.a = a;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Objects.hashCode(this.a);
            return hash;
        }
    }
    
    private static class C{
        String a;
        public C(String a){
            this.a = a;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Objects.hashCode(this.a);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final C other = (C) obj;
            return Objects.equals(this.a, other.a);
        }
    }
    
    private static class D{
        String a;
        public D(String a){
            this.a = a;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final D other = (D) obj;
            return Objects.equals(this.a, other.a);
        }
    }
}
