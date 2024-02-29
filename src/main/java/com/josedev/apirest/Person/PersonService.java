package com.josedev.apirest.Person;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;
    
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
        return personRepo.findByFirstname(name);
    }

    public Object deletePersonById(int personID)
    {
        personRepo.deleteById(personID);
        return null; 
        
    }
}