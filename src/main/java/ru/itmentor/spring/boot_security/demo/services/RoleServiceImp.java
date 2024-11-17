package ru.itmentor.spring.boot_security.demo.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Service

public class RoleServiceImp implements ru.itmentor.spring.boot_security.demo.service.RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
       return roleRepository.findAll();
    }
}
