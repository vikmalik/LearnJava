/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.java.general;

/**
 *
 * @author vikmalik
 */
public class StringOperations {
    
    public static void main(String[] args){
        StringOperations instance = new StringOperations();
        instance.testReplaceAll();
    }
    
    private void testReplaceAll(){
        String str = "[Key]:][\"value\"";
        System.out.println(str + " is replaced with - " + str.replaceAll("(\\])|(\\[)|(\")", " "));
    }
    
}
