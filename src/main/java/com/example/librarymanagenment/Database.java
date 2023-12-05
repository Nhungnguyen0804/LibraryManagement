package com.example.librarymanagenment;
import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
    public static Connection connectDB(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
//        MY DATABASE IS marcoData
            Connection connect;
            connect = DriverManager.getConnection("jdbc:mysql://localhost/library_management", "root", "08042004");
            return connect;
        }catch(Exception e){e.printStackTrace();}

        return null;
    }
}
