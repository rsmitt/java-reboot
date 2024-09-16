package ru.edu.module12.service;

import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import ru.edu.module12.entity.User;
        import ru.edu.module12.exception.ItemNotFoundException;
        import ru.edu.module12.repository.UserRepository;

import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

        import static org.assertj.core.api.Assertions.assertThat;
        import static org.junit.jupiter.api.Assertions.*;

        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<User> cars = new ArrayList<>(Arrays.asList(
                new User(1L, "Anton", 25),
                new User(2L, "Dmitry", 35)
        ));

        when(repository.findAll()).thenReturn(cars);

        List<User> allUsers = service.findAll();
        assertThat(allUsers.size()).isGreaterThan(0);
    }

    @Test
    void findById() {
        User user = new User(1L, "Anton", 25);

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        User singleUser = service.findById(1L);
        assertThat(singleUser).isNotNull().isEqualTo(user);
    }

    @Test
    void save() {
        User user = new User(1L, "Anton", 25);

        when(repository.save(user)).thenReturn(user);
        User savedCar = service.save(user);
        assertThat(savedCar.getName()).isNotNull();
    }

    @Test
    void update() {
        User user = new User(1L, "Anton", 25);

        when(repository.save(user)).thenReturn(user);

        User savedCar = service.save(user);
        assertThat(savedCar.getName()).isNotNull();
    }

    @Test
    void deleteById() {
        User user = new User(1L, "Anton", 25);

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void itemNotFoundException() {
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> service.deleteById(1L));
        assertEquals("User not found, id = 1", exception.getMessage());
    }
}
