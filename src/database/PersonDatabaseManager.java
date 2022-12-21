package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.Person;

public class PersonDatabaseManager implements DataManager {
    
    private final Connection conn;
    private final File file;
    private final String table;

    public PersonDatabaseManager(File file,String table) throws SQLException {
        this.file = file;
        this.table = table;
        this.conn = DriverManager.getConnection("jdbc:sqlite:"+file.getAbsolutePath());
    }

    @Override
    public void Export(List list) {
        try {
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS`EMAIL` (\n" +
                                           "	`Id`	INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                           "	`Mail`	TEXT NOT NULL\n" +
                                           ");");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Iterable Import() {
        return new Iterable<Person>(){
            @Override
            public Iterator<Person> iterator() {
                try {
                    return createIterator();
                } catch (SQLException e) {
                    System.out.println(e);
                    return Collections.emptyIterator();
                }
            }
        };
    }
    
    private Iterator<Person> createIterator() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM"+" "+table);
        
        return new Iterator<Person>(){
            
            Person nextPerson = nextPersonIn(rs);
            
            @Override
            public boolean hasNext() {
                return nextPerson != null;
            }

            @Override
            public Person next() {
                Person per = nextPerson;
                nextPerson = nextPersonIn(rs);
                return per;
            }
        };
    }            
    
    private Person nextPersonIn(ResultSet rs) {
        return next(rs) ? personIn(rs):null;
    }

    private boolean next(ResultSet rs) {
        try {
            boolean next = rs.next();
            if (next) return true;
            rs.close();
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    private Person personIn(ResultSet rs) {
        return new Person(getInt(rs, "Id"),
                          getString(rs, "Name"),
                          getString(rs, "Surnames"),
                          getString(rs, "Department"));
    }
    
    private int getInt(ResultSet rs, String field){
        try {
            return rs.getInt(field);
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
    
    private String getString(ResultSet rs, String field){
        try {
            return rs.getString(field);
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
}
