package harouane.DAO;

import harouane.Entities.Participation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PartecipationDAO {
    EntityManager em;

    public PartecipationDAO(EntityManager em) {
        this.em = em;
    }

    public void saveNewPartecipation(Participation participation){
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        em.persist(participation);
        transaction.commit();
        System.out.println("La partecipazione è stata salvata correttamente");
    }

    public Participation getPartecipationById(long id){
     return em.find(Participation.class, id);
    }
}
