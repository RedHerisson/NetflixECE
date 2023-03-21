import Controller.BDD;
import Model.dao.PersonAccessor;
import Model.map.Person;

import java.sql.SQLException;

public class UnitTest {

    public static void main( String[] args) {
        try {
            PersonAccessor personAccessor = new PersonAccessor();
            System.out.println(personAccessor.find(1).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
