package ru.edu.module12.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.module12.entity.User;
import ru.edu.module12.service.UserService;

import java.util.List;

// http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping(value = "api/v1/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService service;


    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        service.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/users/" + user.getId());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);

    }

    @PutMapping
    @Operation(summary = "Update a user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        service.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by id")
    public ResponseEntity<User> deleteById(@PathVariable long userId) {
        service.deleteById(userId);
        return ResponseEntity.ok().build();
    }


}
