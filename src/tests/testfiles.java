package tests;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class testfiles {

    public void main() throws IOException {
        //--write
        File file = new File("/home/lesha/db.txt");
        FileWriter fw = new FileWriter(file, true); // true for appending
        PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
        pw.println("Line 4");
        pw.println("Line 5");
        pw.println("Line 6");
        pw.close();
        //--read
        File myFile = new File("/home/lesha/db.txt");
        //String n = s.next(); //gets you the next token;
        try (Scanner s = new Scanner(myFile)) {
            //String n = s.next(); //gets you the next token;
            while ( s.hasNext()){ //gets you the next line as a string;
                System.out.println(s.nextLine());
            }
        } //gets you the next line as a string;
    }
}
