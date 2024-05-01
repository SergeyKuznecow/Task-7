package ru.itmentor.spring.boot_security.demo.repositories;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleRepository {

    public List<Role> getAllRoles();
    public List<Role> findByIdRoles(List<Long> id);
}
