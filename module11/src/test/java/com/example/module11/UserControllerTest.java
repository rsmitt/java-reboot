package com.example.module11;

import com.example.module11.entity.MyUser;
import com.example.module11.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        List<MyUser> users = Arrays.asList(new MyUser(1L, "Alice", 30), new MyUser(2L, "Bob", 25));
        given(userRepository.findAll()).willReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    public void testCreateUser() throws Exception {
        MyUser newUser = new MyUser(null, "Charlie", 35);
        MyUser savedUser = new MyUser(3L, "Charlie", 35);
        given(userRepository.save(any(MyUser.class))).willReturn(savedUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Long userId = 1L;
        MyUser existingUser = new MyUser(userId, "Alice", 30);
        MyUser updatedUser = new MyUser(userId, "Alice Updated", 35);
        given(userRepository.findById(userId)).willReturn(Optional.of(existingUser));
        given(userRepository.save(existingUser)).willReturn(updatedUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(updatedUser);

        mockMvc.perform(put("/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;
        MyUser existingUser = new MyUser(userId, "Alice", 30);
        given(userRepository.findById(userId)).willReturn(Optional.of(existingUser));
        doNothing().when(userRepository).delete(existingUser);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        MyUser user = new MyUser(userId, "Alice", 30);
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.age").value(30));
    }
}