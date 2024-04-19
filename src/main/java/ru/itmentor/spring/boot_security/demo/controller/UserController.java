package ru.itmentor.spring.boot_security.demo.controller;

import ru.itmentor.spring.boot_security.demo.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String userGet(Principal principal, Model model) {
        model.addAttribute("usergetter", userService.findByUsername(principal.getName()));
        return "user-panel";
    }
}
