package ru.itmentor.spring.boot_security.demo.services;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserServices {

    User findByUsername(String username);

    User getUserById(Long id);

    User save(User user);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    Role findByName(String name);

    List<User> findByNameOrEmail(String search);

    @Transactional
    User createUser(User user);

    User readUser(Long id);

    @Transactional
    void updateUser(User user);

    void deleteUser(Long id);

    List<User> getUsersAndRoles();

    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}