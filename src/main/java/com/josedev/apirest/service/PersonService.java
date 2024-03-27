package com.josedev.apirest.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.josedev.apirest.Person.Person;
import com.josedev.apirest.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;
    
    @SuppressWarnings("null")
    public void createPersona(Person person)
    {
        personRepo.save(person);
    }

    public List<Person> allPerson()
    {
        List<Person> persons = personRepo.findAll();
        if (persons.isEmpty()) {
            throw new EntityNotFoundException("No se encontro ningun registro");            
        }
        return persons;
    }

    public Optional<Person> getPersonById(int personID)
    {
        Optional<Person> persons = personRepo.findById(personID);
        if(persons.isEmpty()){
            throw new EntityNotFoundException("No se encontro personas con el id: " + personID);
        } 
        return persons;
    }
    
    public List<Person> getPersonByName(String name)
    {
        List<Person> persons = personRepo.findByFirstname(name);
        if(persons.isEmpty())
        {
            throw new EntityNotFoundException("No se encontro personas con el nombre: "+ name);
        }
        return persons;
    }

    public ResponseEntity<String> deletePersonById(int personID)
    {
        if(personRepo.existsById(personID)){
            personRepo.deleteById(personID);
            return ResponseEntity.status(HttpStatus.OK).body("Registro eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "No se encontro ningun registro"); 
        }
    }

    public void updatePerson(Person person, int personID)
    {
        Optional<Person> existingPersonOptional = personRepo.findById(personID);
        if(existingPersonOptional.isPresent()){
            Person existingPerson = existingPersonOptional.get();

            existingPerson.setFirstname(person.getFirstname());
            existingPerson.setLastname(person.getLastname());
            existingPerson.setCity(person.getCity());
            existingPerson.setEmail(person.getEmail());

            personRepo.save(existingPerson);
        } else {
            throw new EntityNotFoundException("Person with ID " + personID + " not found" ); 
        }
    }
}