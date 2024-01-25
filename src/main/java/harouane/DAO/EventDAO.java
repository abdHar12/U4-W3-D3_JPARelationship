package harouane.DAO;

import harouane.Entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventDAO {
private final EntityManager em;

    public EventDAO(EntityManager em) {
        this.em = em;
    }
    public void saveNewEvent(Event event){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("Evento \'"+ event.getTitle()+"\' salvato correttamente");
    }

    public Event getEventById(long id){
        return em.find(Event.class, id);
    }

    public void deleteById(long id){
        EntityTransaction transaction=em.getTransaction();
        Event found=getEventById(id);
        if(found!=null){
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Elemento cancellato con successo!");
        }else System.out.println("Elemento non trovato, quindi non cancellato!");
    }

}
