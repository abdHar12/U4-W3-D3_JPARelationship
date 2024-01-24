package harouane.Entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    long id;

    @Column
    String name;
    @Column
    String citta;

    public Location(String name, String citta) {
        this.name = name;
        this.citta = citta;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getName() {
        return name;
    }

    public String getCitta() {
        return citta;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
