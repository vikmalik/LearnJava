package com.learnjava.general;

import java.util.StringTokenizer;

/**
 *
 * @author vikmalik
 */
public class TestTokenizer {
    public static void main(String[] args){
        String str = "rtsp://10.194.118.193/8142fcbb55db1?token=HQidFFL7BXfYIr1tMeddgq+qsf9iOamzsn/GhBJVbEaFUpK/6G/kXDqrwQNMWHiKYLSq50opM5Z77ZxKTbnQlRKOIQS8WH1CR14Xe34CF2KPEAvKE33pULX0ZKUEwf66";
        StringTokenizer st = new StringTokenizer(str,"/",true);
        
        int counter = 0;
        
        StringBuilder sb = new StringBuilder();
                
        while(st.hasMoreTokens()){
            counter++;
            if(counter == 3){
                sb.append("/archive");
            }
            
            sb.append(st.nextToken());
        }
        
        System.out.println("New text = " + sb);
        
    }
}
