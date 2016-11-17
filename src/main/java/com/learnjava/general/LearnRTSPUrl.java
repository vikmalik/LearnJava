package com.learnjava.general;

/**
 *
 * @author vikmalik
 */
public class LearnRTSPUrl {
    public static void main(String[] args){
        String s = "rtsp://10.10.01.01/adsa";
        
        //s = s.substring("rtsp://".length());
        
        s = s.replace("rtsp://", "http://");
        System.out.println(s);
    }
}
