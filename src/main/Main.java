package main;
import database.PersonDatabaseManager;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

public class Main {
    
    public static void main(String[] args) {
        PersonDatabaseManager manager;
        List<String> lista= new ArrayList<String>();
        try {
            manager= new PersonDatabaseManager(new File("./src/KATA5.db"),"PERSONS");
        } catch (SQLException ex) {
            manager=null;
            System.out.println(ex);
        }
        
        manager.Export(lista);
        
        for(Object person : manager.Import()){
            System.out.println(person.toString());
        }
    }
}
