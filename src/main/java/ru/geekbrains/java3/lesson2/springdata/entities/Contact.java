package ru.geekbrains.java3.lesson2.springdata.entities;

import javax.persistence.*;


@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
//    @Column(name = "fname")
    private String firstName;
    private String lastName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact() {
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact that = (Contact) o;
        if (id != that.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = new Integer(String.valueOf(id));
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                '}';
    }
}
