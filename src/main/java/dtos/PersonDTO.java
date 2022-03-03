/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Address;
import entities.Person;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private String fName;
    private String lName;
    private String phone;
    private int id;
    private String street;
    private String city;
    private int zip;

    public PersonDTO(Person person) {
        this.fName = person.getFirstName();
        this.lName = person.getLastName();
        this.phone = person.getPhone();
        if(person.getId() != 0) {
            this.id = person.getId();
        }
        if(person.getAddress() != null){
            this.street = person.getAddress().getStreet();
            this.city = person.getAddress().getCity();
            this.zip = person.getAddress().getZipCode();
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
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && zip == personDTO.zip && Objects.equals(fName, personDTO.fName) && Objects.equals(lName, personDTO.lName) && Objects.equals(phone, personDTO.phone) && Objects.equals(street, personDTO.street) && Objects.equals(city, personDTO.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, phone, id, street, city, zip);
    }
}
