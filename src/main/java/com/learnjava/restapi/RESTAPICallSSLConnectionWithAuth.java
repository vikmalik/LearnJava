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
public class RESTAPICallSSLConnectionWithAuth {
    private static final String url = "https://10.126.135.66:8440/ora/queryService/query/getSessionBySessionId?value=90614f035e69941";

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
        urlCon.setRequestProperty("Cookie", "JSESSIONID=E7C848FA19206F6A745CC3757C1500F0");

        // Tell the url connection object to use our socket factory which bypasses security checks
        ((HttpsURLConnection) urlCon).setSSLSocketFactory(sslSocketFactory);

        urlCon.connect();
        InputStream  input = ((HttpsURLConnection) urlCon).getInputStream();
        FileOutputStream output = new FileOutputStream("c:/temp/session.txt");
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
