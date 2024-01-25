package harouane.Entities;

import harouane.enums.StateParticipation;

import javax.persistence.*;

@Entity
@Table(name = "partecipations")
public class Participation {
    @Id
    @GeneratedValue
    long id;
    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    Person person;
    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @Column(name = "state_participation")
    @Enumerated(EnumType.STRING)
    StateParticipation stateParticipation;

    public Participation() {
    }

    public Participation(Person person, Event event, StateParticipation stateParticipation) {
        this.person= person;
        this.event = event;
        this.stateParticipation = stateParticipation;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Event getEvent() {
        return event;
    }

    public StateParticipation getStateParticipation() {
        return stateParticipation;
    }


    public void setStateParticipation(StateParticipation stateParticipation) {
        this.stateParticipation = stateParticipation;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "personId=" + person +
                ", event=" + event +
                ", stateParticipation=" + stateParticipation +
                '}';
    }
}
