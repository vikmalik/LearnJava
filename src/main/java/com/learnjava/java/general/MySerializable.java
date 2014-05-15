/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.java.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author vikmalik
 */
public class MySerializable implements Serializable{

    public static void main(String...args){
        AnotherClass a = new AnotherClass();
        AnotherClass b;
        //Serialize
        try {
            FileOutputStream fout = new FileOutputStream("MyFile.ser");
            ObjectOutputStream Oout = new ObjectOutputStream(fout);
            Oout.writeObject(a);
            System.out.println( a.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //De-serialize
        try {
            FileInputStream fis = new FileInputStream("MyFile.ser");
            ObjectInputStream  Oin = new ObjectInputStream (fis); 
            b = (AnotherClass) Oin.readObject();
            System.out.println( b.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

class AnotherClass  implements Serializable{  

    transient int x = 8;  
    static int y;  

    @Override  
    public String toString() {  
        return "x : " + x + ", y :" + y;  
    }  
}
