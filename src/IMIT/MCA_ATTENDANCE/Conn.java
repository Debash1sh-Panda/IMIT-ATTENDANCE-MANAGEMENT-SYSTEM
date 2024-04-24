package IMIT.MCA_ATTENDANCE;

import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
    Conn(){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///mcaattendancedatabase", "root", "MyDeb@2002");
            s = c.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}