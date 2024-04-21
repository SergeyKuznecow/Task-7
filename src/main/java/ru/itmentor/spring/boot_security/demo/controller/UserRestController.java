package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserServiceImp userService;

    @Autowired
    public UserRestController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> userGet(Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }
}
