package com.Vue;
import java.sql.Connection;
import java.sql.DriverManager;


//class used for the connection to the mysql server
public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "";
        String databaseUser = "";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

}
