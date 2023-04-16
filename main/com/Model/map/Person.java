package com.Model.map;

/**
 * Classe abstraite qui permet de définir les méthodes communes aux modèles
 */
public class Person extends BdModel {

    protected String name;

    protected String surname;

    protected int age;

    protected String sexe;

    public Person() {
        super(-1);
        this.name = "";
        this.surname = "";
        this.age = 0;
        this.sexe = "M";
    }

    public Person(int id, String name, String surname, int age, String sexe) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sexe = sexe;
    }

    public Person(Person person) {
        super(person.getId());
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

    @Override
    public String getTableName() {
        return "Person";
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
