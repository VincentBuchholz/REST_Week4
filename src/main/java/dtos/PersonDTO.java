/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private String fName;
    private String lName;
    private String phone;
    private int id;

    public PersonDTO(Person person) {
        this.fName = person.getFirstName();
        this.lName = person.getLastName();
        this.phone = person.getPhone();
        if(person.getId() != 0) {
            this.id = person.getId();
        }
    }
    
    public static List<PersonDTO> getDtos(List<Person> personList){
        List<PersonDTO> personDTOS = new ArrayList();
        personList.forEach(person->personDTOS.add(new PersonDTO(person)));
        return personDTOS;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
