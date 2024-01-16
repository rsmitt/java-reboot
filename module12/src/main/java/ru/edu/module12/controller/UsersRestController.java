package ru.edu.module12.controller;

import org.springframework.web.bind.annotation.*;
import ru.edu.module12.entity.MyUser;
import ru.edu.module12.repository.MyUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersRestController {
    private MyUserRepository myUserRepository;
    public UsersRestController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping
    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }
    @PostMapping
    public MyUser createUser(@RequestBody MyUser user) {
        return myUserRepository.save(user);
    }
    @PutMapping("/{id}")
    public MyUser updateUser(@PathVariable Long id, @RequestBody MyUser myUserDetails) {
        MyUser myUser = myUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        myUser.setName(myUserDetails.getName());
        myUser.setAge(myUserDetails.getAge());
        return myUserRepository.save(myUser);
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Long id) {
        MyUser myUser = myUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        myUserRepository.delete(myUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/{id}")
    public MyUser getUserById(@PathVariable Long id) {
        return myUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

}
