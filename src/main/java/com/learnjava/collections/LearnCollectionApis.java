package com.learnjava.collections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vikmalik
 */
public class LearnCollectionApis {
    
    public static void main(String[] args){
        doMain(args);
    }
    
    private static void doMain(String[] args){
        List globalList = new ArrayList();
        globalList.add("a");
        globalList.add("b");
        globalList.add("c");
        
        
        List myList = new ArrayList();
    
        myList.add("a");
        myList.add("b");
        
        
        System.out.println("com.learnjava.collections.LearnCollectionApis.doMain(): Global List Contains all element of my list : " + globalList.containsAll(myList) );
        
        myList.add("d");
        System.out.println("com.learnjava.collections.LearnCollectionApis.doMain(): Global List Contains all element of my list : " + globalList.containsAll(myList) );
        
    }
}
