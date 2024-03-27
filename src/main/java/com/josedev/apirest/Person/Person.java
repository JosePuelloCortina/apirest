package com.josedev.apirest.Person;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer personID;
    @Basic
    private String lastname;
    private String firstname;
    private String email;
    private String city; 
}
