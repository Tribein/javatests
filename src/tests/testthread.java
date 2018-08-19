package tests;

public class testthread extends Thread{
    
    private String testVar;
    private boolean shutdown = true;
    public testthread (String test){
        testVar = test;
    }
    
    @Override
    public void run() {
        while(!shutdown){
            System.out.println(testVar);
        }
    }    
    
}
