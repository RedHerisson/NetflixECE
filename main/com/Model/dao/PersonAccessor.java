package com.Model.dao;

import com.Model.map.Person;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Accessor for Person
 */



public class PersonAccessor extends Accessor<Person> {

    public PersonAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    /**
     * Get Person from id from sqlBDD
     * @param id SQL id of the object
     * @return Person object found
     * @throws SQLException error while accessing the dataBase
     */
    @Override
    public Person findById(int id) throws SQLException, IOException, ClassNotFoundException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM `Person` WHERE ID = " + id );

        if ( result.next() ) {
            String name = result.getString(2);
            String surname = result.getString(3);
            int age =result.getInt(4);
            String sexe = result.getString(5);
            return new Person(id, name, surname, age, sexe);
        }
        return null;
    }

    /**
     * Get Person from name from sqlBDD
     *
     * @param name SQL name of the object
     * @return Person object found
     * @throws SQLException error while accessing the dataBase
     */


    public int countUsersBySexe(String sexe) throws SQLException, ClassNotFoundException, IOException {
        int cpt=0;

        ResultSet result = dataBase.getRequest().executeQuery("SELECT COUNT(*) FROM Person p JOIN User u ON p.ID=u.person_id WHERE sexe = '"+sexe+"'");
        if (result.next()){
            cpt=result.getInt(1);
        }
        return cpt;
    }

    public int countUsersByAge(int age1, int age2) throws SQLException, ClassNotFoundException, IOException {
        int cpt=0;

        ResultSet result = dataBase.getRequest().executeQuery("SELECT COUNT(*) FROM Person p JOIN User u ON p.ID=u.person_id WHERE age BETWEEN "+age1+" AND "+age2+"");;
        if (result.next()){
            cpt=result.getInt(1);
        }
        return cpt;
    }


    /**
     * Creation d'un utilisateur dans la base de données
     * @param person Personne à créer
     * @return ID de la base de données mis à jour de l'utilisateur créé
     * @throws SQLException erreur SQL
     */
    @Override
    public int create(Person person) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO Person(name, surname, age, sexe) VALUES (?,?,?,?) ;" );

        pre.setString(1, person.getName());
        pre.setString(2, person.getSurname());
        pre.setInt(3, person.getAge());
        pre.setString(4, person.getSexe());

        pre.executeUpdate();
        return dataBase.getLastIdFromTable("Person");
    }

    /**
     * Mise à jour de tout l'utilisateur dans la base de données
     * @param person
     * @return
     * @throws SQLException
     * @warning uniquement si une grande majorité des champs sont modifiés
     */
    public int update(Person person) throws SQLException {
        System.out.println(person.getId());
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Person WHERE name = '" + person.getName() + "' AND surname = '" + person.getSurname() + "' AND age = " + person.getAge() + " AND sexe = '" + person.getSexe() + "'");
        if( ! result.next() ) {
            return create( person );
        }
        else {
            int PersonId = result.getInt(1);
            System.out.println("update");
            System.out.println(person.toString());
            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE Person SET name = ? , surname = ?, age = ?, sexe = ? WHERE ID = " + person.getId());
            pre.setString(1, person.getName());
            pre.setString(2, person.getSurname());
            pre.setInt(3, person.getAge());
            pre.setString(4, person.getSexe());

            pre.executeUpdate();
            return PersonId;
        }
    }

    /**
     * Mise à jour du nom de la personne
     * @param person Personne à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateName(Person person) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET name = ? WHERE ID = " + person.getId());
        pre.setString(1, person.getName());
        pre.executeUpdate();
    }

    /**
     * Mise à jour du prénom de la personne
     * @param person Personne à mettre à jour
     * @throws SQLException  erreur SQL
     */
    public void updateSurname(Person person) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET surname = ? WHERE ID = " + person.getId());
        pre.setString(1, person.getSurname());
        pre.executeUpdate();
    }

    /**
     * Mise à jour de l'age de la personne
     * @param person Personne à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateAge(Person person) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET age = ? WHERE ID = " + person.getId());
        pre.setInt(1, person.getAge());
        pre.executeUpdate();
    }

   /**
     * Mise à jour du sexe de la personne
     * @param person Personne à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateSexe(Person person) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET sexe = ? WHERE ID = " + person.getId());
        pre.setString(1, person.getSexe());
        pre.executeUpdate();
    }

    /**
     * Suppression d'un utilisateur dans la base de données
     * @param id ID de l'utilisateur à supprimer
     * @throws SQLException erreur SQL
     */
    @Override
    public void delete( int id ) throws SQLException {
        dataBase.getRequest().executeUpdate("DELETE FROM Person WHERE ID =" + id );

    }
}
