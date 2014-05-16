package com.learnjava.general;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vikmalik
 */
public class LearnGenerics {

    private static class A {
    }

    private static class B extends A implements C {
    }

    private interface C {
    }

    private static class D extends B {
    }

    public static void main(String... args) {

        A a = new A();
        B b = new B();
        D d = new D();

        ArrayList<? super A> l = new ArrayList<>();
        l.add(a);
        l.add(b);
        l.add(d);
        A getA = (A) l.get(0);

        ArrayList<? super C> l1 = new ArrayList<>();
        //l1.add(a);
        l1.add(b);
        l1.add(d);

        ArrayList<A> lA = new ArrayList<>();
        //method1(lA);
        //method2(lA);

        ArrayList<B> lB = new ArrayList<>();
        method1(lB);
        //incompatible types: ArrayList<B> cannot be converted to List<? super C>
        //method2(lB);

        ArrayList<C> lC = new ArrayList<>();
        method1(lC);
        method2(lC);

        ArrayList<D> lD = new ArrayList<>();
        lD.add(d);
        method1(lD);
        //incompatible types: ArrayList<D> cannot be converted to List<? super C>
        //method2(lD);

    }

    private static void method1(List<? extends C> l) {
    }

    private static void method2(List<? super C> l) {
    }

    private <T> void getValue(T x) {
    }

    private static final class Algorithm {

        public static <T> T max(T x, T y) {
            //Error: "l bad operand types for binary operator '>'"
            //return x > y ? x : y;
            return x;
        }
    }

    public static void print(List<? extends Number> list) {
        for (Number n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

}


class Singleton<T> {
    
    //Cannot make static referecne to non static variable
    //private static Singleton<T> instance = null;
    
    private Singleton<T> instance = null;
    
    public Singleton<T> getInstance() {
        if (instance == null) {
            instance = new Singleton<T>();
        }

        return instance;
    }

    
}