package ru.sberbank.edu;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.ALL_VALUE)
public class UserViewController {

    @GetMapping("/page")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @GetMapping("/getUsers")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getUsers");
        return modelAndView;
    }

    @GetMapping("/addUser")
    public ModelAndView postUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postUser");
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteUser");
        return modelAndView;
    }

    @GetMapping("/editUser")
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

}
