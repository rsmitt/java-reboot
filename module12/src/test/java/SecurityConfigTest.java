import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.edu.module12.config.SecurityConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(SecurityConfig.class) // Импорт SecurityConfig
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        // Настройка MockMvc для интеграции с Spring Security
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void shouldAllowAccessToGetRequest() throws Exception {
        // GET запросы должны быть разрешены всем пользователям
        mvc.perform(get("/users"))
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