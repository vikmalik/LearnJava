package com.learnjava.general;

/**
 *
 * @author vikmalik
 */
public class LearnParameterizedEnum {

    public static enum MyEnum {
        Vikas("My Name is Vikas."),
        Sachin("My Name is Sachin");

        String welcomeMessage;

        MyEnum(String welcomeMessage) {
            this.welcomeMessage = welcomeMessage;
        }

        public String getWelcomeMessage() {
            return welcomeMessage;
        }
    };
    
    public static void main(String[] args){
        String myName = "Vikas";
        MyEnum e = MyEnum.valueOf(myName);
        System.out.println("Welcome Message: " + e.getWelcomeMessage());
        
        myName = "Sachin";
        e = MyEnum.valueOf(myName);
        System.out.println("Welcome Message: " + e.getWelcomeMessage());
    }

}
