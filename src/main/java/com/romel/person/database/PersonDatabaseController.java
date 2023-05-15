package com.romel.person.database;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class PersonDatabaseController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    public PersonDatabaseController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }


    @GetMapping("/personDatabase")
    public Iterable<Person> get(){
        return personService.findAll();
    }

    @GetMapping("/personDatabase/{id}")
    public Person get(@PathVariable Integer id){
        Person person = personService.get(id);
        if(person == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return person;
    }

    @DeleteMapping("/personDatabase/{id}")
    public void delete (@PathVariable Integer id){
        personService.remove(id);
    }

//    @PostMapping("/personDatabase")
//    public Person create(@RequestBody Person person){
//        personService.save(person);
//        return person;
//    }

    @PostMapping("/personDatabase")
    public Person create(@RequestBody Person person){
        return personService.save(person.getName());
    }

    @PutMapping("/personDatabase/{id}")
    Person changePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
            return personRepository.findById(id)
                    .map(person -> {
                        person.setName(newPerson.getName());
                        return personRepository.save(person);
                    })
                    .orElseGet(() -> {
                        newPerson.setId(id);
                        return personRepository.save(newPerson);

                    });
//        return db.replace(id, new Person(person.getId(), person.getName()));
    }

}


