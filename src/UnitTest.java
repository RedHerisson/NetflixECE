import Controller.BDD;

import java.sql.SQLException;

public class UnitTest {

    public static void main( String[] args) {
        try {
            BDD dataBase = new BDD("192.168.1.14", "Netflix", "root", "T9Eu%v5z3_");
            dataBase.getData("SELECT * FROM Movie");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
