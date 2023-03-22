package com.Model.dao;

import com.Model.map.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Person find(int id) throws SQLException {
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

    @Override
    public int create(Person person) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO Person(name, surname, age, sexe) VALUES (?,?,?,?) ;" );

        pre.setString(1, person.getName());
        pre.setString(2, person.getSurname());
        pre.setInt(3, person.getAge());
        pre.setString(4, person.getSexe());

        pre.executeUpdate();

        ResultSet result = dataBase.getRequest().executeQuery("SELECT * FROM Person ORDER BY id DESC LIMIT 1 ");
        if( result.next() ) {
            return result.getInt(1);
        }
        return -1;

    }

    @Override
    public int update(Person person) throws SQLException {
        System.out.println(person.getID());
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Person WHERE ID = " + person.getID() );
        result.
        if( ! result.next() ) {
            return create( person );
        }
        else {

            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE Person SET  ID = ?, name = ? , surname = ?, age = ?, sexe = ? ;" );
            pre.setInt(1, person.getID());
            pre.setString(2, person.getName());
            pre.setString(3, person.getSurname());
            pre.setInt(4, person.getAge());
            pre.setString(5, person.getSexe());

            pre.executeUpdate();
            return person.getID();
        }
    }

    @Override
    public void delete( int id ) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery("DELETE FROM Person WHERE ID =" + id );

    }
}
