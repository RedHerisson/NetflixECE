package com.Vue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//class used for the connection to the mysql server
public class DatabaseConnection {

    public Connection databaseLink;

    static String user = "root";
    static String password = "T9Eu%v5z3_";
    static String url = "jdbc:mysql://localhost/login";
    static String driver = "com.mysql.cj.jdbc.Driver";

    private static String ip = "90.90.95.91";
    private static String name = "Netflix";

    public static Connection getConnection() {
        Connection connection = null;

            try {
                Class.forName(driver);

                try {
                    connection = DriverManager.getConnection(url, user, password);
                }
                catch(SQLException e){
                    throw new RuntimeException(e);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        return connection;
        }


    }




