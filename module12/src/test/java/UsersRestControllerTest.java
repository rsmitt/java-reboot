import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.module12.controller.UsersRestController;
import ru.edu.module12.entity.MyUser;
import ru.edu.module12.repository.MyUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootConfiguration
public class UsersRestControllerTest {

    @Mock
    private MyUserRepository myUserRepository;

    @InjectMocks
    private UsersRestController usersRestController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(usersRestController).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        List<MyUser> users = Arrays.asList(new MyUser(1L, "Alice", 30), new MyUser(2L, "Bob", 25));
        given(myUserRepository.findAll()).willReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    public void testCreateUser() throws Exception {
        MyUser newUser = new MyUser(null, "Charlie", 35);
        MyUser savedUser = new MyUser(3L, "Charlie", 35);
        given(myUserRepository.save(any(MyUser.class))).willReturn(savedUser);

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
        given(myUserRepository.findById(userId)).willReturn(Optional.of(existingUser));
        given(myUserRepository.save(existingUser)).willReturn(updatedUser);

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
        given(myUserRepository.findById(userId)).willReturn(Optional.of(existingUser));
        doNothing().when(myUserRepository).delete(existingUser);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        MyUser user = new MyUser(userId, "Alice", 30);
        given(myUserRepository.findById(userId)).willReturn(Optional.of(user));

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.age").value(30));
    }
}