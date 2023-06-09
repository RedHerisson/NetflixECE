package com.Controller;

import java.sql.*;

/**
 * Connexion et requete à la base de données distante
 *
 * @author Raphaël Jeantet
 *
 */
public class BDD {

    private static String ip = "90.90.95.91";
    private static String name = "Netflix";
    private static String login = "root";
    private static String pwd = "T9Eu%v5z3_";

    private static Connection session;


    // singleton
    private static BDD bdd;

    /**
     * Constructeur en privé, pour éviter d'avoir plusieurs connexions à la base de données
     */
    private BDD() {
        // instance unique
    }

    /**
     * createur d'instance de la BDD pour les objets DAO
     * @throws SQLException exception en cas de mauvaise connection à la base de données
     * @throws ClassNotFoundException   mauvaise connection au driver SQL
     */
    public static BDD getInstance() throws SQLException, ClassNotFoundException {


        if( bdd == null ) {
            bdd = new BDD();
            Class.forName("com.mysql.cj.jdbc.Driver");
            bdd.session = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + name , login, pwd );
        }
        return bdd;


    }

    public Statement getRequest() throws SQLException {
        return session.createStatement();
    }

    public int getLastIdFromTable(String table ) throws SQLException {
        ResultSet result = getRequest().executeQuery("SELECT * FROM " + table +" ORDER BY id DESC LIMIT 1 ");
        if( result.next() ) {
            return result.getInt(1);
        }
        return 0;
    }
}
