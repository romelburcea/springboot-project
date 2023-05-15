package com.romel.person.database;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {
    private final PersonRepository repository;
//    private Map<Integer, Person> db = new HashMap<>(){{
//        put(1, new Person(1, "Romel"));
//        put(2, new Person(2, "Ozana"));
//    }};

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    public Person get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        repository.deleteById(id);
    }

//    public void save(Person person) {
//        db.put(person.getId(), person);
//    }
    public Person save(String name) {
        Person person = new Person();
        person.setName(name);
        repository.save(person);
        return person;
    }


//    public Person change(Integer id, Person person) {
//        return repository.findById(id)
//                .map(person1 -> {
//                    person1.setName(person1.getName());
//                    return repository.save(person1);
//                })
//                .orElseGet(() -> {
//                    person.setId(id);
//                    return repository.save(person);
//
//                });
////        return db.replace(id, new Person(person.getId(), person.getName()));
//    }
}
