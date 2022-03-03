package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import errorhandling.ExceptionDTO;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        Person person = new Person(fName,lName,phone);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        }finally {
            em.close();
        }
    }



    @Override
    public PersonDTO deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try{
            Person p = em.find(Person.class,id);
            if(p == null){
                throw new PersonNotFoundException("Could not delete, provided id does not exist");
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return new PersonDTO(p);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Person person =em.find(Person.class,id) ;

            if(person == null){
                throw new PersonNotFoundException("No person with provided id found");
            }
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> tq = em.createQuery("select p from Person p",Person.class);
        return PersonDTO.getDtos(tq.getResultList());
        }finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        try{
           Person person = em.find(Person.class,p.getId());
           if(p.getfName() != null) {
               person.setFirstName(p.getfName());
           }
           if(p.getlName() != null) {
               person.setLastName(p.getlName());
           }
           if(p.getPhone() != null) {
               person.setPhone(p.getPhone());
           }
           person.setLastEdit(new Date());

           em.getTransaction().begin();
           em.merge(person);
           em.getTransaction().commit();

           return p;

        } finally {
            em.close();
        }
    }

    public void addAddress(Address address){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
