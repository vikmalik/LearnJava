/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.general;

import java.util.HashMap;

/**
 *
 * @author vikmalik
 */
public class StringOperations {
    
    public static void main(String[] args){
        StringOperations instance = new StringOperations();
        instance.testReplaceAll();
        instance.testStringEquality();
    }
    
    private void testReplaceAll(){
        String str = "[Key]:][\"value\"";
        System.out.println(str + " is replaced with - " + str.replaceAll("(\\])|(\\[)|(\")", " "));
    }
    
    private void testStringEquality(){
        String a1 = "abc";
        String a2 = new String("abc");
        String a3 = new String("abc");
        String a4 = new String(a3);
        
        System.out.println("a1=a2" + (a1==a2));
        System.out.println("a1=a3" + (a1==a3));
        System.out.println("a2=a3" + (a2==a3));
        System.out.println("a3=a4" + (a3==a4));
        
    }   
}
