package ru.edu.module12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.module12.entity.User;
import ru.edu.module12.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
