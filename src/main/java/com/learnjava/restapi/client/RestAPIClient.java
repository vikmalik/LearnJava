package com.learnjava.restapi.client;

/**
 *
 * @author vikmalik
 */
public class RestAPIClient {
    public static void main(String[] args) {
       
       NewJerseyClient client = new NewJerseyClient();
       
        System.out.println("Output = " + client.getJson());
       client.close();

    }
}
