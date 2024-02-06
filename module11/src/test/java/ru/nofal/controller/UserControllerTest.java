package ru.nofal.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.nofal.jpa.controller.UserJpaController;
import ru.nofal.jpa.entity.UserJpa;
import ru.nofal.jpa.repository.UserJpaRepository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserJpaController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserJpaRepository repository;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            StandardCharsets.UTF_8);

    List<UserJpa> users = new ArrayList<>();

    @BeforeEach
    public void addUsers() {
        UserJpa user1 = new UserJpa();
        user1.setId(1L);
        user1.setName("Name1");
        user1.setAge(30);

        UserJpa user2 = new UserJpa();
        user1.setId(2L);
        user1.setName("Name2");
        user1.setAge(40);

        repository.save(user1);
        repository.save(user2);

        users.add(user1);
        users.add(user2);
    }

    @Test
    public void findAllShouldReturnAllUsers() throws Exception {
        Mockito.when(this.repository.findAll()).thenReturn(users);

        mvc.perform(get("/home/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(content().string(containsString("User_id")));
    }
}
