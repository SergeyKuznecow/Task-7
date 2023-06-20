package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Person;
import ru.itmentor.spring.boot_security.demo.service.PersonService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {

        this.personService = personService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        model.addAttribute("people", personService.getAllUsers());
        return "show_all";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("person", personService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String createNewPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personService.addPerson(person);
        return "show_all";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("person", personService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") Long id) {
        personService.updateUser(person);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personService.removeUser(id);
        return "redirect:/admin";
    }


}
