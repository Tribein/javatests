package tests;

import java.io.FileInputStream;
import java.util.Properties;

public class testproperties {
    
    private Properties properties = new Properties();
    private String value;
    
    public void test() throws Exception {
            properties.load(new FileInputStream("/home/test/test.properties"));
            //file literally should containt line "PARAMETER=VALUE"
            value = properties.getProperty("PARAMETER");        
            System.out.println(value);
    }
    
}
