/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.restapi.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author vikmalik
 */
public class ApacheHttpRestClient1 {
    public static void main(String[] args) {
    DefaultHttpClient httpclient = new DefaultHttpClient();
    try {
      // specify the host, protocol, and port
      HttpHost target = new HttpHost("localhost", 8080, "http");
       
      // specify the get request
      HttpGet getRequest = new HttpGet("/LearnJavaEE7/service/sample1");
      getRequest.addHeader("Authorization", "Bearer YWRtaW46YXJpY2VudDEyMw==");
 
      System.out.println("executing request to " + target);
 
      HttpResponse httpResponse = httpclient.execute(target, getRequest);
      HttpEntity entity = httpResponse.getEntity();
 
      System.out.println("----------------------------------------");
      System.out.println(httpResponse.getStatusLine());
      Header[] headers = httpResponse.getAllHeaders();
      for (int i = 0; i < headers.length; i++) {
        System.out.println(headers[i]);
      }
      System.out.println("----------------------------------------");
 
      if (entity != null) {
        System.out.println(EntityUtils.toString(entity));
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // When HttpClient instance is no longer needed,
      // shut down the connection manager to ensure
      // immediate deallocation of all system resources
      httpclient.getConnectionManager().shutdown();
    }
  }
}
