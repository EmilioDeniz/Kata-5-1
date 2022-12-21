package database;

import java.io.File;
import java.util.List;
import model.Person;

public interface DataManager {
    
    public void Export(List<String> list,String table);
    public Iterable<Person> Import();
}
