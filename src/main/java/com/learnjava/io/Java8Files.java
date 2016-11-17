/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikmalik
 */
public class Java8Files {
    
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\vikmalik\\Pictures\\23-04-2014");
        try {
            Iterator<Path> itr =  Files.list(path).iterator();
            
            while(itr.hasNext()){

                Path fileName = itr.next().getFileName();
                System.out.println(fileName);
                
                Path sourcePath = Paths.get("C:\\Users\\vikmalik\\Pictures\\23-04-2014\\" + fileName);
                
                Path destPath = Paths.get(sourcePath + ".jpg");
                Files.move(sourcePath, destPath);
            }
        } catch (IOException ex) {
            Logger.getLogger(Java8Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
