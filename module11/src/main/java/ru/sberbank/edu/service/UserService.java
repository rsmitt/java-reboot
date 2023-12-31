package ru.sberbank.edu.service;

import ru.sberbank.edu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.edu.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    @Autowired
    private final UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.getById(id);
    }

    public User updateUserById(long id, String name, int age) {
        User user = userRepository.getById(id);
        user.setName(name);
        user.setAge(age);
        userRepository.save(user);
        return user;
    }

    public void add(long id, String name, int age) {
        userRepository.saveOrUpdate(id, name, age);
    }

    public void delete(long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

}
