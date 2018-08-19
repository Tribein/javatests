package tests;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class testdate {
    
    private long unixts;
    private Date javadate;
    private final SimpleDateFormat sdtf = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
    
    public void test() throws Exception{
        //get current date
        javadate = new Date();
        System.out.println(javadate);
        //get date in UNIX format, seconds dince 1970-01-01 00:00:00
        unixts = Instant.now().getEpochSecond();
        System.out.println(unixts);
        //parse date from string
        javadate = sdtf.parse("2011.11.19 23:59:58");
        System.out.println(javadate);
    }
    
}
