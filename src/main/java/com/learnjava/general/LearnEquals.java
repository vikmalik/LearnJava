/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.general;

/**
 *
 * @author vikmalik
 */
public class LearnEquals {
    
    private static class A{
        int i;
    }
    
    public static void main(String[] Args){
        A a1 = new A();
        A a2 = new A();
        
        a1.i = 1;
        a2.i = 1;
        
        System.out.println("a1 ==  a2" + (a1 == a2) );
        System.out.println("a1.equal(a2)" + a1.equals(a2) );
    }
}
