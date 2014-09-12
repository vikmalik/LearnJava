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
        
        
        System.out.printf("true ^ true = %b\n", true ^ true);
        System.out.printf("true ^ false = %b\n", true ^ false);
        System.out.printf("false ^ false = %b\n", false ^ false);

        System.out.printf("1>>>32 = %s :: %s :: %s\n",
                Long.toBinaryString(i),
                Long.toBinaryString(i >>> 32),
                Long.toBinaryString((i ^ (i >>> 32))));
    }
}