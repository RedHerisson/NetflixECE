package Controller;

import java.sql.*;
import java.util.*;

/**
 * Connexion et requete à la base de données distante
 *
 * @author Raphaël Jeantet
 *
 */
public class BDD {

    private Connection session;

    private Statement statement;

    private ResultSet result;

    /**
     * Contructeur de la base données
     * @param ip    ip du serveur de la base données
     * @param name  nom de la base de données sur le serveur
     * @param login login de la base de données
     * @param pwd   mot de passe de la base de données
     * @throws SQLException exception en cas de mauvaise connection à la base de données
     * @throws ClassNotFoundException   mauvaise connection au driver SQL
     */
    public BDD(String ip, String name, String login, String pwd) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        session = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + name , login, pwd );
        statement = session.createStatement();
    }

    public ArrayList<Object> getData( String request ) throws SQLException {

        ArrayList<Object> FormatedResult = null;

        result = statement.executeQuery(request);

        ResultSetMetaData metaData = result.getMetaData();

        int nbColonne = metaData.getColumnCount();

        while(result.next()) {
            //result.
        }

        return FormatedResult;


    }


}
