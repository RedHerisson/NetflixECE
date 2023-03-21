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

    public Person(int id, String name, String surname, int age, String sexe) {
        this.bddID = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sexe = sexe;
    }

    public Person(Person person) {
        this.bddID = person.bddID;
        this.name = person.name;
        this.surname = person.surname;
        this.age = person.age;
        this.sexe = person.sexe;

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

    public int getID() { return bddID; }

    public void setBddID(int bddID) {
        this.bddID = bddID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String toString() {
        return "Person | name : " + name + " surname : " + surname + " | age : " + age + " sexe : " + sexe ;
    }

}
