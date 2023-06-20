package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Person;

import java.util.List;

@Repository
public interface PersonDAO {
    void saveUser(Person person);

    void updateUser(Person person);

    void removeUser(Long id);

    Person getUserById(Long id);

    List<Person> getAllUsers();

    Person findByUsername(String name);

}
