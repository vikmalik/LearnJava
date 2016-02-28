package com.learnjava.general;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author vikmalik
 */
public class LearnBase64EncodeDecode {
 
    public static void main(String[] args) {
        //String encodedString = "jOSTqPRgqjF7wzR46mjV/mJSl7JeBLKPiFCL3MsQ";
        //String encodedString = "A7BLIEc65Iy5YLLgsDE+5r29xZNlo05jGuVYVjsg";
        String encodedString = "Gxt7ximKX5DWdoKqSFZMQAv/uAWhZQzq+RWX8LGS";
        if(Base64.isBase64(encodedString)){
            String decodedString = new String(Base64.decodeBase64(encodedString));
            System.out.println("Decoded String of " + encodedString +  " is : " + decodedString);
            
            byte[] decodedBytes = Base64.decodeBase64(encodedString);
            System.out.println("Decoded key length = " + decodedBytes.length);
            for(byte b : decodedBytes){
                System.out.print(String.format("%X ", b));
                //System.out.print(String.format("%02X ", b));
            }
            System.out.println();
            
            decodedString = Base64.encodeBase64String(decodedString.getBytes());
            System.out.println("Re-encoded value (String) = " + decodedString);
            
            decodedString = Base64.encodeBase64String(Base64.decodeBase64(encodedString));
            System.out.println("Re-encoded value (Byte) = " + decodedString);
            
            if(encodedString.equals(decodedString)){
                System.out.println("Decoding is successful");
            }else{
                System.out.println("Decoding is un-successful");
            }
        }else {
            System.out.println("Not base64 String");
        }
    }
}
