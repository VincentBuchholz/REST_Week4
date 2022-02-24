/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;

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
        fe.addPerson("Vincent","Buchholz","22882183");
        fe.addPerson("Viktor","Bak","369163916");
        fe.addPerson("Karl","Larsen","12890171");
    }
    
    public static void main(String[] args) {
        populate();
    }
}
