package com.josedev.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josedev.apirest.Person.Person;
import com.josedev.apirest.service.PersonService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    
    private final PersonService personService;

    @PostMapping
    public void createPersona(@RequestBody Person person)
    {
       personService.createPersona(person);
    }

    @GetMapping("/allperson")
    public ResponseEntity<?> allPerson()
    {
        try {
            List<Person> persons = personService.allPerson();
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") int personID)
    {
        try {
            Optional<Person> personId = personService.getPersonById(personID);
            return ResponseEntity.ok(personId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getPersonByName(@PathVariable("name") String firstname)
    {
        try {
            List<Person> persons = personService.getPersonByName(firstname);
            return ResponseEntity.ok(persons);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable("id") int personID)
    {
        try {
            Optional<Person> persons = personService.getPersonById(personID);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public void updatePerson(@PathVariable("id") int personID, @RequestBody Person person) {      
        personService.updatePerson(person, personID);
    }
}