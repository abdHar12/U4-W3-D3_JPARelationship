package harouane.DAO;

import harouane.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonDAO {
    EntityManager em;

    public PersonDAO(EntityManager em) {
        this.em = em;
    }
    public void saveNewPerson(Person person){
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        em.persist(person);
        transaction.commit();
        System.out.println("Persona "+ person.getFirstName() + " " + person.getLastName()+"salvata correttamente");
    }
    public Person getPersonById(long id){
        return em.find(Person.class, id);
    }

}
