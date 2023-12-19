package ru.sberbank.edu;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.ALL_VALUE)
public class UserViewController {

    @GetMapping
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Inside UserController");
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
