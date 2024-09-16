package ru.sberbank.edu.controller;

import ru.sberbank.edu.entity.User;
import ru.sberbank.edu.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UserController.class})
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;


    @Test
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Ilia", 25),
                new User(2L, "Roman",  35)
        ));
        when(service.findAll()).thenReturn(users);
        mockMvc.perform(get("/api/v1/user/getUsersPage"))
                .andDo(print())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }


    @Test
    void deleteUserById() throws Exception {
        mockMvc.perform(post("/api/v1/user/deleteUser")
                        .content("{\"id\": 1}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
