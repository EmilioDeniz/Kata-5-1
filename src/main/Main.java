package main;
import database.FileLoader;
import database.Loader;
import database.MailListReader;
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
       
        try {
            manager= new PersonDatabaseManager(new File("./src/KATA5.db"),"PERSONS");
        } catch (SQLException ex) {
            manager=null;
            System.out.println(ex);
        }
        
        Loader loader = new MailListReader(new FileLoader(new File("./src/email.txt")));
        List<String> lista= loader.load();
        manager.Export(lista,"EMAIL");
        
        for(Object person : manager.Import()){
            System.out.println(person.toString());
        }
    }
}
