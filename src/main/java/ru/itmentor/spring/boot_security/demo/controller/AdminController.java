package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.services.UserServiceImp;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;
    private final RoleService roleService;

    public AdminController(UserServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private void addAllRolesToModel(Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("allRoles", roles);
    }

    @GetMapping()
    public String showUserList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof User){
            List<User> users = userService.getUsersAndRoles();
            model.addAttribute("users", users);
        }
        return "admin/all";
    }

    @GetMapping( "/info/{id}")
    public String infoUser(@PathVariable("id")int id , Model model) {
        model.addAttribute("user",userService.readUser(id));
        return "users/user";
    }

    @GetMapping("/createUser")
    public String newUser(Model model) {
        addAllRolesToModel(model);
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUserInfo(@PathVariable("id") int id, Model model) {
        User user = userService.readUser(id);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "admin/edit";
    }

    @PatchMapping("/editInfo/{id}")
    public String editUserInfo(@ModelAttribute ("user") User user, @PathVariable("id") int id) {
        userService.editeUser(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
