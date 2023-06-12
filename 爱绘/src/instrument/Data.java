package instrument;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Data {
    Connection conn = null;
    Statement stmt = null;
    public  Connection getConnection() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/table1";
        String name = "root";
        String passw = "password";
        Class.forName("com.mysql.cj.jdbc.Drive");
        try {
            Connection conn=DriverManager.getConnection(url,name,passw);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


