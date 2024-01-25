package ru.sberbank.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.edu.entity.User;
import ru.sberbank.edu.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest({UserController.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @InjectMocks
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check receiving all users")
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Anton", 30),
                new User(2L, "Nikita", 38)
        ));

        when(service.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users/"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    @DisplayName("check receiving single user details")
    void getUserById() throws Exception {
        User user = new User(1L, "Anton", 30);

        when(service.findById(anyLong())).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.name").value("Anton"))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @DisplayName("check adding a new user")
    void createUser() throws Exception {
        User user = new User(1L, "Anton", 30);

        when(service.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/admin/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
                )
                .andDo(print())
                //.andExpect(header().string("Location", "api/v1/admin/" + user.getId()))
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("check updating a user")
    void updateUser() throws Exception {
        User user = new User(1L, "Anton", 30);

        when(service.update(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/v1/admin/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
                )
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("check deleting a user by id")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteUserById() throws Exception {

        doNothing().when(service).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/admin/1"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }
}
