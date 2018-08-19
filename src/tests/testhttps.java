package tests;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;


public class testhttps {
    public void test() throws Exception{
        String targeturl = "https://example.net";
        String postbody = "TEST";
        String httpsmethod = "POST";
            URL url = new URL(targeturl);
            //--
            System.out.println(url.getHost());
            SSLParameters sslParameters = new SSLParameters();
            List sniHostNames = new ArrayList(1);
            sniHostNames.add(new SNIHostName(url.getHost()));
            sslParameters.setServerNames(sniHostNames);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, null, new java.security.SecureRandom());
            SSLSocketFactory wrappedSSLSocketFactory = new SSLSocketFactoryWrapper(sc.getSocketFactory(), sslParameters);
            HttpsURLConnection.setDefaultSSLSocketFactory(wrappedSSLSocketFactory);
            //--            
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            //connection.setConnectTimeout(20000);
            connection.setRequestMethod(httpsmethod);
            connection.addRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("Content-Length", Integer.toString(postbody.length()));
            connection.setDoOutput(true);
            connection.setDoInput(true);
            if ( postbody.length()>0){
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                output.writeBytes(postbody);
            }
            DataInputStream input = new DataInputStream(connection.getInputStream());
            for (int c = input.read(); c != -1; c = input.read()) {
                System.out.print((char) c);
            }
    }
}
