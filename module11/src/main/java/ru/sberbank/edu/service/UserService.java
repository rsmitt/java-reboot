package ru.sberbank.edu.service;

import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import ru.sberbank.edu.entity.User;
        import ru.sberbank.edu.exception.ItemNotFoundException;
        import ru.sberbank.edu.repository.UserRepository;

        import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("User not found, id = " + id));
    }

    public User save(User car) {
        return repository.save(car);
    }

    public User update(User car) {
        findById(car.getId());
        return repository.save(car);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}