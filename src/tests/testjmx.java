package tests;

import java.util.HashSet;
import java.util.Set;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.*;

public class testjmx {
    
    private String hostname = "localhost";
    private String jmxport = "3334";
    
    public void test() throws Exception{
        Set<ObjectName> mbeans = new HashSet<ObjectName>();
        JMXServiceURL target = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+hostname+":"+jmxport+"/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(target);
        MBeanServerConnection remote = connector.getMBeanServerConnection();
        mbeans.addAll(remote.queryNames(null,null));
        for (ObjectName mbean : mbeans) {
            System.out.println(mbean.toString());
            //System.out.println(remote.getAttribute(mbean,"Name"));
        }
        connector.close();
    }
    
}
