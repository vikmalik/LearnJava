/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.learnmokito;

import java.sql.SQLException;

/**
 *
 * @author vikmalik
 */
public class Producer {
    private final String buffer;
    
    public Producer(String buffer){
        System.out.println("Producer Constructor Called");
        this.buffer = buffer;
    }
    
    public String produceMethod() throws SQLException{
        System.out.println("produceMethod method called");
        return getDataFromDB(buffer);
    }
    
    private String getDataFromDB(String query) throws SQLException{
        System.out.println("getDataFromDB method called");
        String returnValue = "db" + query;
        return returnValue;
    }
}
