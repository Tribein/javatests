package tests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class testhttp {
    public void test() throws Exception{
            URL url = new URL ("http://cs.au.dk/~amoeller/WWW/webservices/GoogleSearch.wsdl");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("POST");
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            String postbody = "";
            /* basic auth
            String creds = "username:password";
            String credsbase64 = DatatypeConverter.printBase64Binary(creds.getBytes());                    
            connection.setRequestProperty  ("Authorization", "Basic " + credsbase64);
            connection.addRequestProperty("Content-Type", "application/xml");
            */
            if (postbody != null && !postbody.isEmpty()) {
                connection.setRequestProperty("Content-Length", Integer.toString(postbody.length()));
                connection.getOutputStream().write(postbody.getBytes("UTF8"));
                connection.getOutputStream().close();
            }
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in = new BufferedReader (new InputStreamReader (content));
            String line;
            String response="";
            while ((line = in.readLine()) != null) {
                response = response+line;
            }           
            in.close();
            System.out.println(response);           
    }
}
