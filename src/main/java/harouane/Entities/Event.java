package harouane.Entities;

import harouane.enums.EventType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
