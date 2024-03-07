package com.josedev.apirest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.josedev.apirest.Person.Person;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index(Model model){

        String url = "http://localhost:8080/person/allperson";
        ResponseEntity<Person[]> response = restTemplate.getForEntity(url, Person[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Person[] persons = response.getBody();
            model.addAttribute("personList", persons);
        }else{
            model.addAttribute("error", "No se pudieron obtener los datos de las personas");
        }
        return "index";
    }
}