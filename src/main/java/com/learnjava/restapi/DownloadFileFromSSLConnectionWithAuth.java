/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learnjava.restapi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author vikmalik
 */
public class DownloadFileFromSSLConnectionWithAuth {
    private static final String url = null;

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        // Install the all-trusting trust manager
        HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new X509TrustManager[]{new NullX509TrustManager()}, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        
        
        // All set up, we can get a resource through https now:
        final URLConnection urlCon = new URL(url).openConnection();
        urlCon.setRequestProperty("Cookie", "JSESSIONID=" + "C9971616C40090A58DC7F5403E5B68B1");

        // Tell the url connection object to use our socket factory which bypasses security checks
        ((HttpsURLConnection) urlCon).setSSLSocketFactory(sslSocketFactory);

        urlCon.connect();
        InputStream  input = ((HttpsURLConnection) urlCon).getInputStream();
        FileOutputStream output = new FileOutputStream("c:/temp/temp.mp4");
        byte data[] = new byte[4096];
        //long total = 0;
        int count;
        while ((count = input.read(data)) != -1) {
            output.write(data, 0, count);
        }
    }

    private static class NullHostNameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            
            return true;
        }
    }
    
    private static class NullX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws java.security.cert.CertificateException {
            
        }

        @Override
        public void checkServerTrusted(X509Certificate[] xcs, String string) throws java.security.cert.CertificateException {
            
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    
    
}

}
