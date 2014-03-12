/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.learnmokito;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikmalik
 */
public class Consumer {
    
    public int consumerMethod(Producer producer){
        int returnVal = -1;
        if(producer != null){
            try {
                if(null != producer.produceMethod()){
                    returnVal = 0;
                }else{
                    returnVal = -3;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, "Exception occured in producer", ex);
                returnVal = -2;
            }
        }
        return returnVal;
    }
}
