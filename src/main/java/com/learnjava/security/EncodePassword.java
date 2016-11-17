package com.learnjava.security;

import java.util.Base64;
import java.util.Base64.Encoder;

/**
 *
 * @author vikmalik
 */
public class EncodePassword {
    public static void main(String[] args) {
        Encoder encoder = Base64.getEncoder();
        System.out.println("Encoded Password = " + encoder.encodeToString("testme".getBytes()));
    }
}
