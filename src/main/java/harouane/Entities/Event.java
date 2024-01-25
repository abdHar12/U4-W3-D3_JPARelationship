package harouane.Entities;

import harouane.enums.EventType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private long id;
    @Column
    String title;
    @Column
    LocalDate date;
    @Column
    String description;

    @Column
    @Enumerated(EnumType.STRING)
    EventType eventType;

    @Column(name="max_number_participants")
    Integer maxNumberParticipants;

    @OneToMany(mappedBy = "event")
    List<Participation> participationsList;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

    @ManyToMany
    @JoinTable(
            name="events_persons",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name="person_id")
    )
    Set<Person> persons;

    public Event() {
    }

    public Event(String title, LocalDate date, String description, EventType eventType, Integer maxNumberParticipants, Location location) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.eventType = eventType;
        this.maxNumberParticipants = maxNumberParticipants;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Participation> getParticipationsList() {
        return participationsList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setMaxNumberParticipants(Integer maxNumberParticipants) {
        this.maxNumberParticipants = maxNumberParticipants;
    }

    public void setParticipationsList(List<Participation> participationsList) {
        this.participationsList = participationsList;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                ", maxNumberParticipants=" + maxNumberParticipants +
                '}';
    }

}
