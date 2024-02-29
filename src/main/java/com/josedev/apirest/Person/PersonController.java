package com.josedev.apirest.Person;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
    public List<Person> allPerson()
    {
        return personService.allPerson();
    }

    @GetMapping("/id/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") int personID)
    {
        return personService.getPersonById(personID);
    }

    @GetMapping("/search/{name}")
    public List<Person> getPersonByName(@PathVariable("name") String firstname)
    {
        return personService.getPersonByName(firstname);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deletePersonById(@PathVariable("id") int personID)
    {
        personService.deletePersonById(personID);
    }
}