package harouane.Entities;

import harouane.enums.Genre;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue
    long id;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;

    @Column
    String email;

    @Column (name = "dateOfBirthday")
    Date dateOfBirthday;

    @Column
    @Enumerated(EnumType.STRING)
    Genre genre;

    @OneToMany(mappedBy = "person")
    List<Participation> participations;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, Date dateOfBirthday, Genre genre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;
        this.genre = genre;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", genre=" + genre +
                '}';
    }
}
