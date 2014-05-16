package com.learnjava.general;

/**
 * ">>" This will shift LSB bit & Sign bit will remain intact
 * "<<" This will shift MSB bit & Sign bit will remain intact
 * ">>>" This will shift LSB bit & Sign bit will be propagated
 * 
 * @author vikmalik
 */
public class BitwiseOperatorExample1 {
    public static void main(String... args){
        int i = 1;
        System.out.println("Binary Representation of " + i + ": " + Integer.toBinaryString(i));
        System.out.println("temp " + i + ": " + Math.pow(2, 31));
        System.out.println("i>>>"+ 1 +"  : " + (i>>>1));
        System.out.println("i>>>-"+ 1 +" : " + ((-i)>>>1));
        System.out.println("i>>"+ 1 +"   : " + (i>>1));
        System.out.println("i>>-"+ 1 +"  : " + ((-i)>>1));
        System.out.println("i<<"+ 1 +"   : " + (i<<1));
        System.out.println("i<<-"+ 1 +"  : " + ((-i)<<1)); 
    }
}