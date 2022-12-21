package model;

public class Person {

    private final int id;
    private final String name,surnames,department;

    public Person(int id, String name, String surnames, String department) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.department = department;
    }
    
    @Override
    public String toString(){
     return "Id: "+id+" Name: "+name+" Surnames: "+surnames+" Department: "+department;   
    }
}
