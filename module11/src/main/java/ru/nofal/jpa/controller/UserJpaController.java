package ru.nofal.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nofal.jpa.entity.UserJpa;
import ru.nofal.jpa.repository.UserJpaRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
public class UserJpaController {

    UserJpaRepository repository;

    @Autowired
    public void setRepository(UserJpaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/createUserWithPath/{id}/{name}/{age}")
    public String createUserWithPath(@PathVariable("id") long id,
                                     @PathVariable("name") String name,
                                     @PathVariable("age") int age,
                                     Model model) {
        UserJpa user = new UserJpa();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        model.addAttribute("newUser", user);
        if (repository.findAll().contains(user)) {
            return "viewUserExists";
        } else {
            repository.save(user);
            return "viewAddNewUser";
        }
    }

    @PostMapping("/createUserWithJson")
    public String createUserWithJson(@RequestBody UserJpa user, Model model) {
        model.addAttribute("newUser", user);
        if (repository.findAll().contains(user)) {
            return "viewUserExists";
        } else {
            repository.save(user);
            return "viewAddNewUserFromJson";
        }
    }

    @PutMapping("/updateUserWithPath/{id}/{name}/{age}")
    public String updateUserWithPath(@PathVariable("id") long id,
                                     @PathVariable("name") String name,
                                     @PathVariable("age") int age,
                                     Model model) throws CloneNotSupportedException {
        UserJpa user = new UserJpa();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        model.addAttribute("updUser", user);
        if (repository.findById(user.getId()).isEmpty()) {
            return "viewUserNotExists";
        } else {
            model.addAttribute("oldUser", repository.findById(id).orElseThrow().clone());
            repository.save(user);
            return "viewUpdatedUser";
        }
    }

    @PutMapping("/updateUserWithJson")
    public String updateUserWithJson(@RequestBody UserJpa user, Model model) throws CloneNotSupportedException {
        model.addAttribute("updUser", user);
        if (repository.findById(user.getId()).isEmpty()) {
            return "viewUserNotExists";
        } else {
            model.addAttribute("oldUser", repository.findById(user.getId()).orElseThrow().clone());
            repository.save(user);
            return "viewUpdatedUser";
        }
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers(Model model) {
        List<UserJpa> users = repository.findAll();
        if (users.isEmpty()) {
            return "viewNoFoundUsers";
        } else {
            model.addAttribute("allUsers", users);
            return "viewAllUsers";
        }
    }

    @GetMapping("/getUserById/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        if (repository.findById(id).isEmpty()) {
            model.addAttribute("userId", id);
            return "viewUserNotFoundById";
        } else {
            List<UserJpa> users = List.of(repository.findById(id).orElseThrow());
            model.addAttribute("allUsers", users);
            return "viewAllUsers";
        }
    }

    @GetMapping("/getUserByJson")
    public String getUserByJson(@RequestBody UserJpa user, Model model) {
        if (!repository.findAll().contains(user)) {
            model.addAttribute("updUser", user);
            return "viewUserNotExists";
        } else {
            List<UserJpa> users = List.of(repository.findById(user.getId()).orElseThrow());
            model.addAttribute("allUsers", users);
            return "viewAllUsers";
        }
    }

    @DeleteMapping("/deleteUserById/{id}")
    public String deleteUserById(@PathVariable("id") long id, Model model) throws CloneNotSupportedException {
        if (repository.findById(id).isEmpty()) {
            model.addAttribute("userId", id);
            return "viewUserNotFoundById";
        } else {
            model.addAttribute("deletedUser", repository.findById(id).orElseThrow().clone());
            repository.deleteById(id);
            return "viewUserByIdWasDeleted";
        }
    }

    @DeleteMapping("/deleteUserByJson")
    public String deleteUserByJson(@RequestBody UserJpa user, Model model) throws CloneNotSupportedException {
        if (!repository.findAll().contains(user)) {
            model.addAttribute("updUser", user);
            return "viewUserNotExists";
        } else {
            model.addAttribute("deletedUser", repository.findById(user.getId()).orElseThrow().clone());
            repository.delete(user);
            return "viewUserWasDeleted";
        }
    }
}
