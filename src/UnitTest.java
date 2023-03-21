import Controller.BDD;
import Model.dao.PersonAccessor;
import Model.map.Person;

import java.sql.SQLException;

public class UnitTest {

    public static void main( String[] args) {
        try {
            PersonAccessor personAccessor = new PersonAccessor();

            Person demoPerson;
            demoPerson = personAccessor.find(1);
            if( demoPerson == null) {
                System.out.println("object does not exist");
            }
            else {
                demoPerson.setName("igor");
                System.out.println();
                personAccessor.update(demoPerson);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
