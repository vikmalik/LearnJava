/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.java.general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikmalik
 */
public class CountNumberOfLines {
    public static void main(String[] args){
        LineNumberReader  lnr = null;
        try {
            lnr = new LineNumberReader(new FileReader(new File("C:\\Users\\vikmalik\\Documents\\personal\\My e-books\\Technical\\NetBeansProjects\\LearnJava\\data\\bookstore-jaxb.xml")));
            lnr.skip(Long.MAX_VALUE);
            System.out.println(lnr.getLineNumber());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CountNumberOfLines.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CountNumberOfLines.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(lnr != null){
                try {
                    lnr.close();
                } catch (IOException ex1) {
                   //do nothing
                }
            }
        }        
    }    
}
