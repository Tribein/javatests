package tests;

public class testinteger {
    
    private int ti = 9;
    private String ts;
    public void test() throws Exception{
        
        //int to string
        ts = String.valueOf(ti);
        System.out.println(ts);
        
        ti++;
        
        ts = Integer.toString(ti);
        System.out.println(ts);
        
        //get integer from string
        ti = Integer.parseInt(ts);
        System.out.println(ti);
        
    }
    
}
