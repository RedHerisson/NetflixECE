package Model.dao;

import Model.map.Person;

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
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM `Person` WHERE ID = 1 ");
        result.next();
        String name = result.getString(2);
        String surname = result.getString(3);
        int age =result.getInt(4);
        String sexe = result.getString(5);

        return new Person(name, surname, age, sexe);
    }

    @Override
    public Person create(Person obj) {
        return null;
    }

    @Override
    public Person update(Person obj) throws SQLException {
        dataBase.getRequest().executeUpdate("INSERT INTO `Person`(`ID`, `name`, `surname`, `age`, `sexe`) VALUES " +
                1 +
                obj.getName() +
                obj.getSurname() +
                obj.getAge() +
                obj.getSexe());

        return null;
    }

    @Override
    public void delete(Person obj) {

    }
}
