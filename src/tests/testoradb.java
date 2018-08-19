package tests;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class testoradb {

    Connection con;
    PreparedStatement prep;
    CallableStatement clst;
    Statement stmt;
    ResultSet rs;
    int c0 = 0;
    String c1 = "Test";
    int [] retvals;

    public void main() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //JDBC THIN
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "test", "test");
            //JDBC OCI
            con = DriverManager.getConnection("jdbc:oracle:oci:@orcl.world", "test", "test");
            con.setAutoCommit(false);
            //--callable statement
            clst = con.prepareCall("{ call pkg_test_jdbc.test_string(?,?,?)}");
            clst.setInt(1, 0);
            clst.setString(2, "Test");
            //if we have some output variable
            clst.registerOutParameter(3, Types.NUMERIC);
            clst.execute();
            System.out.println(clst.getString(3));
             /* 
             //--user data type
             StructDescriptor myoratypedescritpor = StructDescriptor.createDescriptor("T_TEST_JDBC",con);
            // make row of type T_TEST_JDBC
            Object[] myoratypeattrs = new Object[] { c1 };
             STRUCT myoratype = new STRUCT(myoratypedescritpor,con,myoratypeattrs);
             //number of rows
             STRUCT[] myoraplsqltblrows = new STRUCT[100];
             //assign row to custom table
             myoraplsqltblrows[0] = myoratype; 
             //table of T_TEST_JDBC
             ArrayDescriptor myoraplsqltbl = ArrayDescriptor.createDescriptor("TBL_TEST_JDBC", con);
             ARRAY arrayOftests = new ARRAY(myoraplsqltbl, con,myoraplsqltblrows);
             clst.setArray(1,arrayOftests); 
             clst.registerOutParameter(2, Types.VARCHAR);
             clst.execute();
             System.out.println(clst.getString(2));
             clst.close();  
             */
            //--batch dml 
             clst = con.prepareCall("{ call insert into testtab(c1,c2) values ( ? ,? ) returning c0 into ? }"); 
             clst.registerOutParameter(3, Types.NUMERIC);
             clst.setInt(1, c0);
             clst.setString(2, c1);
             clst.addBatch();
             retvals = clst.executeBatch();
             con.commit();
             for( int i : retvals){ 
             System.out.println(i); 
             }
             clst.close();
            /* */
            //--prepared statement
            prep = con.prepareStatement("insert into testuser.testtab2 (content, id) values (:1 , :2 )"/*,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY*/);
            String teststring = "";
            for (int i = 0; i < 90 * 1024; i++) {
                teststring += "AAA";
            }
            //teststring = "";
            Blob testblob;
            testblob = con.createBlob();
            //fill using string variable
            OutputStream os = testblob.setBinaryStream(1L);
            os.write(teststring.getBytes());
            testblob.setBytes(1, teststring.getBytes());
            //fill using file
            File fBlob = new File ( "/home/lesha/111.file" );
            FileInputStream is = new FileInputStream ( fBlob );
            //testblob=null;
            //pass blob as bind variable
            prep.setBytes(1, teststring.getBytes());
            //if we've used outputstream
            prep.setBinaryStream(1, testblob.getBinaryStream(1, testblob.length()),testblob.length());
            //plain way
            prep.setBlob(1, testblob);
            //from file stream
            prep.setBinaryStream (1, is, (int) fBlob.length() );
            prep.addBatch();
            prep.executeBatch();
            prep.execute();
            prep.close();
            /* */
            //--plain statement
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select dump(c1) from testtab where c0=99999");
            while (rs.next()){ 
                System.out.println(rs.getString(1)); 
            }
            rs.close();
            stmt.close();
            /* */
            con.rollback();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
