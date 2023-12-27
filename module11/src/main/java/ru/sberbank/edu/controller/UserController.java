package ru.sberbank.edu.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sberbank.edu.entity.User;
import ru.sberbank.edu.exception.ItemNotFoundException;
import ru.sberbank.edu.service.UserService;

import java.util.List;

// http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping(value = "/api/v1/user", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @GetMapping("/page")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @GetMapping("/getUsersPage")
    public ModelAndView getUsers() {
        List<User> users = service.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("getUsers");
        return modelAndView;
    }

    @GetMapping("/addUserPage")
    public ModelAndView postUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postUser");
        return modelAndView;
    }

    @PostMapping("/addUser")
    public ModelAndView postUser(@RequestParam("id") Long id, @RequestParam("name") String name,
            @RequestParam("age") Integer age) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = new User(id, name, age);
            if (service.findById(user.getId()).getId() == user.getId()) {
                modelAndView.setViewName("resultAddError");
            }
            return modelAndView;
        } catch (ItemNotFoundException e) {
            User user = new User(id, name, age);
            service.save(user);
            modelAndView.setViewName("resultSucsess");
            return modelAndView;
        }
    }

    @GetMapping("/deleteUserPage")
    public ModelAndView deleteUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteUser");
        return modelAndView;
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            service.deleteById(id);
            modelAndView.setViewName("resultSucsess");
            return modelAndView;
        } catch (MethodArgumentTypeMismatchException e) {
            modelAndView.setViewName("resultError");
            return modelAndView;
        }  catch (ItemNotFoundException e) {
            modelAndView.setViewName("resultFail");
            return modelAndView;
        }
    }

    @GetMapping("/editUserPage")
    public ModelAndView editUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @PostMapping("/editUser")
    public ModelAndView editUser(@RequestParam("id") Long id, @RequestParam("name") String name,
                                 @RequestParam("age") Integer age) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = new User(id, name, age);
            if (service.findById(user.getId()).getId() == user.getId()) {
                service.update(user);
                modelAndView.setViewName("resultSucsess");
                return modelAndView;
            } else {
                modelAndView.setViewName("resultFail");
                return modelAndView;

            }
        } catch (IllegalArgumentException e) {
            modelAndView.setViewName("resultError");
            return modelAndView;
        }
    }



    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.findAll();
        logger.info("getting users list: {}", users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
