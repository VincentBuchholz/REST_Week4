package facades;

import dtos.PersonDTO;
import entities.Person;
import errorhandling.PersonNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(new Person("Vincent","Buchholz","22882183"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new Person("Viktor","Bak","369163916"));
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(new Person("Karl","Larsen","12890171"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void getPersonByIdTest() throws PersonNotFoundException{
        System.out.println("Get Person By id test");
        assertEquals("Vincent",facade.getPerson(1).getfName());
    }

    @Test
    void getAllPersonsTest(){
        System.out.println("Get all persons test");
        assertEquals(3,facade.getAllPersons().size());
    }

    @Test
    void AddPersonTest(){
        System.out.println("Add Person test");
        facade.addPerson("Lars","Hansen","12930983");
        assertEquals(4,facade.getAllPersons().size());
    }

    @Test
    void editPersonTest() throws Exception{
        System.out.println("Edit person test");
        PersonDTO personDTO = new PersonDTO(new Person("Vincent","Buchholz","33333333"));
        personDTO.setId(1);
        facade.editPerson(personDTO);

        assertEquals("33333333",facade.getPerson(1).getPhone());
    }

    @Test
    void deletePersonTest() throws PersonNotFoundException {
        System.out.println("Delete person test");
        facade.deletePerson(3);
        assertEquals(2,facade.getAllPersons().size());
    }

}
