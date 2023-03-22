package com.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Connexion et requete à la base de données distante
 *
 * @author Raphaël Jeantet
 *
 */
public class BDD {

    private static String ip = "192.168.1.14";
    private static String name = "Netflix";
    private static String login = "root";
    private static String pwd = "T9Eu%v5z3_";

    private static Connection session;

    private static Statement request;

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
            bdd.request = session.createStatement();
        }
        return bdd;


    }

    public static Statement getRequest() {
        return request;
    }

    public Image getImageByBlob(Blob blob){
        try {
            InputStream stream = blob.getBinaryStream();
            BufferedImage image = ImageIO.read(stream);
            return image;
        } catch (IOException | SQLException e) {
            System.out.println("Blob-Image conversion Error: " + e.getMessage());
            return null;
        }
    }
}
