import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.edu.module12.Application;
import ru.edu.module12.controller.UsersRestController;
import ru.edu.module12.repository.MyUserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser
public class SecurityConfigTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void shouldAllowAccessToUserRole() throws Exception {
        // Поставка POST-запроса с CSRF и пользователем с ролью ADMIN
        mvc.perform(get("/users")
                .with(csrf())) // CSRF disabled globally, but we can still use it in tests)
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void shouldAllowAccessToAdminRole() throws Exception {
        // Поставка POST-запроса с CSRF и пользователем с ролью ADMIN
        mvc.perform(post("/users")
                .with(csrf()) // CSRF disabled globally, but we can still use it in tests
                .contentType("application/json")
                .content("{\"id\": 2, \"name\": \"Petr\", \"age\": \"12\"}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", roles = "USER")
    @Test
    public void shouldDenyAccessToNonAdminRole() throws Exception {
        // Пользователь с ролью USER не должен иметь доступ к POST /users
        mvc.perform(post("/users")
                .with(csrf())
                .contentType("application/json")
                .content("{\"id\": 2, \"name\": \"Petr\", \"age\": \"12\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldDenyAccessToUnauthenticatedUsers() throws Exception {
        // Неаутентифицированные пользователи не должны иметь доступ к POST /users
        mvc.perform(post("/users")
                .contentType("application/json")
                .content("{\"id\": 2, \"name\": \"Petr\", \"age\": \"12\"}"))
                .andExpect(status().isUnauthorized());
    }
}