package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.PersonDAO;
import ru.itmentor.spring.boot_security.demo.model.Person;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService, UserDetailsService {

    private PersonDAO personDAO;


    @Autowired
    public PersonServiceImp(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public void saveUser(Person person) {
        personDAO.saveUser(person);
    }

    @Override
    @Transactional
    public void updateUser(Person person) {
        personDAO.updateUser(person);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        personDAO.removeUser(id);
    }

    @Override
    public Person getUserById(Long id) {
        return personDAO.getUserById(id);
    }

    @Override
    public List<Person> getAllUsers() {
        return personDAO.getAllUsers();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDAO.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found"));
        }
        return new User(person.getUsername(), person.getPassword(), person.getAuthorities());
    }

    @Transactional
    public void addPerson(Person person) {
        personDAO.saveUser(person);
    }


}

