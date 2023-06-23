package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Person;
import ru.itmentor.spring.boot_security.demo.service.PersonService;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllUsers() {
        return ResponseEntity.ok(personService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok(person);
    }

    @PatchMapping("/update")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        personService.updateUser(person);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> delete(@PathVariable("id") Long id) {
        personService.removeUser(id);
        return ResponseEntity.ok().build();
    }

}
