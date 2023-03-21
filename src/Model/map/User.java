package Model.map;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Person {

    private String pseudo;

    private String pwd;

    private LocalDate acc_creation_date;

    private ArrayList<MovieHistoric> historic;

    private UserData data;

    public User(int id, String pseudo, String pwd, String name, String surname, String email, int age, String sexe, LocalDate acc_creation_date, UserData data) {
        super(id, name, surname, age, sexe );
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.acc_creation_date = acc_creation_date;
        this.historic = new ArrayList<MovieHistoric>();
        this.data = data;
    }
}
