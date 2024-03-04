package com.josedev.apirest.Person;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(int personID)
    {
        return personRepo.findById(personID);
    }
    
    public List<Person> getPersonByName(String name)
    {
        if(personRepo.findByFirstname(name) != null)
        {
            return personRepo.findByFirstname(name);
        }
        return null;
    }

    public ResponseEntity<String> deletePersonById(int personID)
    {
        if(personRepo.existsById(personID)){
            personRepo.deleteById(personID);
            return ResponseEntity.status(HttpStatus.OK).body("Registro eliminado");
        }else{
            System.out.println("no se encuentra");
            return ResponseEntity.status(HttpStatus.OK).body( "No se encontro ningun registro"); 
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