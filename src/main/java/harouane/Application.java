package harouane;

import com.github.javafaker.Faker;
import harouane.DAO.EventDAO;
import harouane.DAO.PartecipationDAO;
import harouane.DAO.PersonDAO;
import harouane.Entities.Event;
import harouane.Entities.Participation;
import harouane.Entities.Person;
import harouane.Entities.enums.EventType;
import harouane.Entities.enums.Genre;
import harouane.Entities.enums.StateParticipation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf =Persistence.createEntityManagerFactory("gestioneeventi");

    private static final Faker faker =new Faker();

    static Supplier<EventType> getRandomEventType=()->{
        Random num = new Random();
        if(num.nextInt(1, 2)==1) return EventType.PRIVATO;
        return EventType.PUBBLICO;
    };
    static Supplier<Genre> getRandomGenre=()->{
        Random num = new Random();
        if(num.nextInt(1, 2)==1) return Genre.M;
        return Genre.F;
    };
    static Supplier<Integer> getRandomNumberPartecipant=()->{
        Random num = new Random();
        return num.nextInt(50, 250);
    };
    static Supplier<StateParticipation> getRandomPartecipationState=()->{
        Random num = new Random();
        if(num.nextInt(1, 2)==1) return StateParticipation.CONFIRMED;
        return StateParticipation.NOT_CONFIRMED;
    };
    static Supplier<Event> getNewEvent=()->{
        return new Event(faker.book().title(), LocalDate.of(new Random().nextInt(2020, 2030), new Random().nextInt(1,12), new Random().nextInt(1,30)), faker.howIMetYourMother().catchPhrase(), getRandomEventType.get(), getRandomNumberPartecipant.get());
    };
    static Supplier<Person> randomPerson=()->{
        return new Person(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.date().birthday(18,50), getRandomGenre.get());
    };
    public static void main(String[] args) {
        EntityManager em= emf.createEntityManager();
        EventDAO eventDAO= new EventDAO(em);
        /*Event event= getNewEvent.get();
        System.out.println(event);
        eventDAO.saveNewEvent(event);
        Event evToFind = eventDAO.getEventById(5);
        if (evToFind == null) System.out.println("Elemento non trovato!");
        else System.out.println("Elemento trovato: " + evToFind);

        eventDAO.deleteById(5);*/

        Event evToFind = eventDAO.getEventById(7);
        PersonDAO personDAO =new PersonDAO(em);
        PartecipationDAO partecipationDAO=new PartecipationDAO(em);
        Person person=randomPerson.get();
        Person personToFind=personDAO.getPersonById(23);
        Participation participation=new Participation(personToFind, evToFind, getRandomPartecipationState.get());
        partecipationDAO.saveNewPartecipation(participation);
        personDAO.saveNewPerson(person);
        personToFind.getParticipations().forEach(part->{
            System.out.println(part);
        });
        evToFind.getParticipationsList().forEach(part->{
            System.out.println(part);
        });
        em.close();
        emf.close();
    }


}
