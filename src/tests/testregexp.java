package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testregexp {
    
    String source="azaza;;foo=barbarbar;;aza;;foo=rabbarr;;za;;fuu=lololo;;atata";
    
    Pattern ptrn = Pattern.compile("foo=([rba]+)|fuu=([lo]+)");
    Matcher mtch;
    
    public void test() throws Exception{
         mtch = ptrn.matcher(source);
         while (mtch.find()){
             for(int i=1;i<=mtch.groupCount();i++){
                 if(mtch.group(i)!=null){
                    System.out.println(mtch.group(i));
                 }
             }
         }
    }

}
