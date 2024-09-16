package ru.sberbank.edu.service;

import ru.sberbank.edu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public User save(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }


}
