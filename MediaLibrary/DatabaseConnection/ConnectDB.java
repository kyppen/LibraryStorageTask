package DatabaseConnection;

import java.sql.*;

public class ConnectDB {
    public ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("We found the connection driver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mediaLibrary?useSSL=false",
                    "root",
                    "Localhostpw123");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
