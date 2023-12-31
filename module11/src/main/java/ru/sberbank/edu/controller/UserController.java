package ru.sberbank.edu.controller;

import org.springframework.http.MediaType;
import ru.sberbank.edu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.edu.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.ALL_VALUE)
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public StringBuffer getAllUsers() {
        List<User> users = userService.findAllUsers();
        StringBuffer sb = new StringBuffer();
        users.forEach(user -> sb.append(user.toString()).append(" ................. "));
        return sb;
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable long userId) {
        User user = userService.findUserById(userId);
        return user.toString();
    }

    @GetMapping("/{userId}/update/name={newName}&age={newAge}")
    public String updateUserById(@PathVariable long userId, @PathVariable String newName, @PathVariable int newAge) {
        return userService.updateUserById(userId, newName, newAge).toString();
    }

    @GetMapping("/create/id={id}&name={name}&age={age}")
    public String createNewUser(@PathVariable long id, @PathVariable String name, @PathVariable int age) {
        userService.add(id, name, age);
        return "Everything is OK! User with id=" + id + " was created.";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUserById(@PathVariable long userId) {
        userService.delete(userId);
        return "Everything is OK! User with id=" + userId + " was deleted.";
    }

}