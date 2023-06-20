package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmentor.spring.boot_security.demo.model.Person;

import java.util.List;

public interface PersonService extends UserDetailsService {
    void saveUser(Person person);

    void updateUser(Person person);

    void removeUser(Long id);

    Person getUserById(Long id);

    List<Person> getAllUsers();

    UserDetails loadUserByUsername(String username);

    public void addPerson(Person person);
}
