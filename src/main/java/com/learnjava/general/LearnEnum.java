package com.learnjava.general;

/**
 *
 * @author vikmalik
 */
public class LearnEnum {
    
    public enum SSOStatus{
        ENABLED,
        DISABLED,
        MIXED
    }
    public static void main(String[] args) {
        int ssoFlag = 1;
        
        SSOStatus mySSOStatus;
        mySSOStatus = SSOStatus.values()[ssoFlag];
        
        System.err.println("MyStatus = " + mySSOStatus);
        System.err.println("MyStatus = " + mySSOStatus.ordinal());
        
        switch(mySSOStatus){
            case ENABLED:
            case DISABLED:
            case MIXED:
        }
    }
   
}
