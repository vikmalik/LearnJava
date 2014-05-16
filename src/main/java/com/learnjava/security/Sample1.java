/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikmalik
 */
public class Sample1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String password = "HelloThere";
        byte[] passwordByte = password.getBytes();
        
        File file = new File(".\\data\\security\\Sample1_data.txt");

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(password);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(passwordByte);
            oos.writeObject(md5.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Sample1.class.getName())
                    .log(Level.SEVERE, "Excetion occured during creation of Message Digest", ex);
        }
        
        Logger.getLogger(Sample1.class.getName()).log(Level.INFO,"Data encription complete");
        
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            
            String tempString = (String)ois.readObject();
                    
            Logger.getLogger(Sample1.class.getName()).log(Level.INFO, "Saved String = {0}", tempString);
            passwordByte = (byte[])ois.readObject();
            
            byte[] newPasswordByte = tempString.getBytes();
            if(md5 != null){
                md5.reset();
                md5.update(newPasswordByte);

                if(Arrays.equals(passwordByte, md5.digest())){
                    Logger.getLogger(Sample1.class.getName()).log(Level.INFO, "MD5 is OK");
                }else{
                    Logger.getLogger(Sample1.class.getName()).log(Level.SEVERE, "Message Digest has been tempered");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sample1.class.getName()).log(Level.SEVERE, "Message Digest has been tempered", ex);
        }
   }
}
