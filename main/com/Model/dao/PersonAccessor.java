package com.Model.dao;

import com.Model.map.Person;

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
        String query = "INSERT INTO Person(name, surname, age, sexe ) VALUES ( '" +
                person.getName() + "', '" +
                person.getSurname() + "', " +
                person.getAge() + ", '" +
                person.getSexe() + "');";
        System.out.println(query);
        dataBase.getRequest().executeUpdate(query);

        ResultSet result = dataBase.getRequest().executeQuery("SELECT * FROM Person ORDER BY id DESC LIMIT 1 ");
        if( result.next() ) {
            return result.getInt(1);
        }
        return -1;

    }

    @Override
    public int update(Person person) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Person WHERE ID = " + person.getID() );

        if( ! result.next() ) {
            return create( person);
        }
        else {

            String query = "UPDATE Person SET " +
                    "ID = " + person.getID() +
                    ", name = '" + person.getName() +
                    "', surname = '" + person.getSurname() +
                    "', age = " +  person.getAge() +
                    ", sexe = '" + person.getSexe() +
                    "' WHERE 1 ";
            System.out.println(query);
            dataBase.getRequest().executeUpdate(query);
        }
        return 0;
    }

    @Override
    public void delete( int id ) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery("DELETE FROM Person WHERE ID =" + id );

    }
}
