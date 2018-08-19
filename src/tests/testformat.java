package tests;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class testformat {
    
    private String fts;
    private final DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final SimpleDateFormat sdtf = new SimpleDateFormat ("yyyy.MM.dd  hh:mm:ss"/*"yyyy.MM.dd 'at' hh:mm:ss a zzz"*/);
    
    public void test() throws Exception{
        
        //format date using datetimeformatter
        fts = LocalDate.now().format(dtfmt);
        System.out.println(fts);
        
        //format date using simpledatetimeformatter
        fts = sdtf.format(new Date());
        System.out.println(fts);
        
        //parse date from string
        System.out.println(sdtf.parse("2011.11.19 23:59:58")); 
    }
    
}
