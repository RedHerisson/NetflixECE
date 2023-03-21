package Model.map;

public class Person {

    private int bddID;
    private String name;

    private String surname;

    private int age;

    private String sexe;

    public Person() {
        this.name = "";
        this.surname = "";
        this.age = 0;
        this.sexe = "M";
    }

    public Person(String name, String surname, int age, String sexe) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sexe = sexe;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getSexe() {
        return sexe;
    }

    public String toString() {
        return "Person | name : " + name + " surname : " + surname + "age : " + age + " sexe : " + sexe ;
    }

}
