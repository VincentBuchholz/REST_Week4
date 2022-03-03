/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getPersonFacade(emf);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            Person p1 = new Person("Vincent","Buchholz","22882183");
            Address a1 = new Address("Ulrikkenborg alle","Lyngby",2800);
            p1.setAddress(a1);
            em.persist(p1);
            em.persist(a1);
            em.getTransaction().commit();
            em.getTransaction().begin();
            Person p2 = new Person("Viktor","Bak","369163916");
            Address a2 = new Address("Amagervej","Amager",4100);
            p2.setAddress(a2);
            em.persist(p2);
            em.persist(a2);
            em.getTransaction().commit();
            em.getTransaction().begin();
            Person p3 =new Person("Karl","Larsen","12890171");
            Address a3 = new Address("Lyngbyvej","Lyngby",2800);
            p3.setAddress(a3);
            em.persist(p3);
            em.persist(a3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }



    }
    
    public static void main(String[] args) {
        populate();
    }
}
