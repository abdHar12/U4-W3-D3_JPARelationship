package harouane.DAO;

import harouane.Entities.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LocationDAO {
    EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void saveNewLocation(Location location){
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        em.persist(location);
        transaction.commit();
        System.out.println("La location è stata salvata correttamente");
    }



    public Location getLocationById(long id){
        return em.find(Location.class, id);
    }
}
