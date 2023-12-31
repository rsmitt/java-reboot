package ru.sberbank.edu.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.edu.entity.User;
import ru.sberbank.edu.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@WebMvcTest({UserController.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    /**
     * Проверка метода getAllUsers у UserController
     */
    @Test
    void getAllUsersTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        List users = new ArrayList();

        users.add(user1);
        users.add(user2);

        when(service.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Igor, age=52) ................. User(id=5, name=Semen, age=44) ................. "));
    }

    /**
     * Проверка метода getUserById у UserController
     */
    @Test
    void getUserByIdTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        when(service.findUserById(4L)).thenReturn(user1);

        mockMvc.perform(get("/users/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Igor, age=52)"));
    }

    /**
     * Проверка метода updateUserById у UserController
     */
    @Test
    void updateUserByIdTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        when(service.updateUserById(4L, "Maga", 15)).thenReturn(new User(4L, "Maga", 15));
        when(service.findUserById(4L)).thenReturn(new User(4L, "Maga", 15));

        mockMvc.perform(get("/users/4/update/name=Maga&age=15"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Maga, age=15)"));
    }

    /**
     * Проверка метода createNewUser у UserController
     */
    @Test
    void createNewUserTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);

        when(service.findUserById(6L)).thenReturn(new User(6L, "Maga", 15));

        mockMvc.perform(get("/users/create/id=6&name=Maga&age=15"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/6"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=6, name=Maga, age=15)"));
    }

    /**
     * Проверка метода deleteUserById у UserController
     */
    @Test
    void deleteUserByIdTest() throws Exception {
        User user1 = new User(4L, "Igor", 52);
        User user2 = new User(5L, "Semen", 44);
        User user3 = new User(6L, "Maga", 15);

        List users = new ArrayList();

        users.add(user1);
        users.add(user2);

        when(service.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users/6/delete"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("User(id=4, name=Igor, age=52) ................. User(id=5, name=Semen, age=44) ................. "));
    }
}
