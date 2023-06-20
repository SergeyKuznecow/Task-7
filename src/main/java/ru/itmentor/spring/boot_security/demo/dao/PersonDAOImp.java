package ru.itmentor.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDAOImp implements PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void updateUser(Person person) {
        entityManager.merge(person);
    }

    @Override
    public void removeUser(Long id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null)
            entityManager.remove(person);
    }

    @Override
    public Person getUserById(Long id) {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public List<Person> getAllUsers() {
        List<Person> personList = entityManager.createQuery("from Person", Person.class).getResultList();
        return personList;
    }

    @Override
    public Person findByUsername(String username) {
        return entityManager.createQuery("FROM Person WHERE username=:username", Person.class).setParameter("username", username).getSingleResult();
    }
}
