package ru.nofal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nofal.entity.User;
import ru.nofal.repo.UserRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
public class UserController {

    UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("admin/createUserWithPath/{id}/{name}/{age}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createUserWithPath(@PathVariable("id") long id,
                                     @PathVariable("name") String name,
                                     @PathVariable("age") int age,
                                     Model model) {
        User user = new User();
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

    @PostMapping("admin/createUserWithJson")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createUserWithJson(@RequestBody User user, Model model) {
        model.addAttribute("newUser", user);
        if (repository.findAll().contains(user)) {
            return "viewUserExists";
        } else {
            repository.save(user);
            return "viewAddNewUserFromJson";
        }
    }

    @PutMapping("admin/updateUserWithPath/{id}/{name}/{age}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateUserWithPath(@PathVariable("id") long id,
                                     @PathVariable("name") String name,
                                     @PathVariable("age") int age,
                                     Model model) throws CloneNotSupportedException {
        User user = new User();
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

    @PutMapping("admin/updateUserWithJson")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateUserWithJson(@RequestBody User user, Model model) throws CloneNotSupportedException {
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
        List<User> users = repository.findAll();
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
            List<User> users = List.of(repository.findById(id).orElseThrow());
            model.addAttribute("allUsers", users);
            return "viewAllUsers";
        }
    }

    @GetMapping("/getUserByJson")
    public String getUserByJson(@RequestBody User user, Model model) {
        if (!repository.findAll().contains(user)) {
            model.addAttribute("updUser", user);
            return "viewUserNotExists";
        } else {
            List<User> users = List.of(repository.findById(user.getId()).orElseThrow());
            model.addAttribute("allUsers", users);
            return "viewAllUsers";
        }
    }

    @DeleteMapping("admin/deleteUserById/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

    @DeleteMapping("admin/deleteUserByJson")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUserByJson(@RequestBody User user, Model model) throws CloneNotSupportedException {
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
