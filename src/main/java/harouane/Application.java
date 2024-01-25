package harouane;

import com.github.javafaker.Faker;
import harouane.DAO.EventDAO;
import harouane.DAO.LocationDAO;
import harouane.DAO.PartecipationDAO;
import harouane.DAO.PersonDAO;
import harouane.Entities.Event;
import harouane.Entities.Location;
import harouane.Entities.Participation;
import harouane.Entities.Person;
import harouane.enums.EventType;
import harouane.enums.Genre;
import harouane.enums.StateParticipation;

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

    static Supplier<Person> randomPerson=()->{
        return new Person(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.date().birthday(18,50), getRandomGenre.get());
    };
    public static void main(String[] args) {
        EntityManager em= emf.createEntityManager();

        /*Event evToFind = eventDAO.getEventById(5);
        if (evToFind == null) System.out.println("Elemento non trovato!");
        else System.out.println("Elemento trovato: " + evToFind);*/


        PersonDAO personDAO =new PersonDAO(em);
        PartecipationDAO partecipationDAO=new PartecipationDAO(em);
        Person person=randomPerson.get();
        Person personToFind=personDAO.getPersonById(23);
        Location location= new Location(faker.beer().name(), faker.address().country());
        LocationDAO locationDAO= new LocationDAO(em);
        locationDAO.saveNewLocation(location);
        EventDAO eventDAO= new EventDAO(em);
        Event event= new Event(faker.book().title(), LocalDate.of(new Random().nextInt(2020, 2030), new Random().nextInt(1,12), new Random().nextInt(1,30)), faker.howIMetYourMother().catchPhrase(), getRandomEventType.get(), getRandomNumberPartecipant.get(), location);
        System.out.println(event);
        eventDAO.saveNewEvent(event);
        Event evToFind=eventDAO.getEventById(40);
        Participation participation=new Participation(personToFind, evToFind, getRandomPartecipationState.get());
        partecipationDAO.saveNewPartecipation(participation);


        //personDAO.saveNewPerson(person);
        em.close();
        emf.close();
    }


}
