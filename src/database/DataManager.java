package database;

import java.io.File;
import java.util.List;
import model.Person;

public interface DataManager<T> {
    
    public void Export(List<String> list);
    public Iterable<Person> Import();
}
