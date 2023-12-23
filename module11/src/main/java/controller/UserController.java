package controller;

import com.example.module11.entity.MyUser;
import com.example.module11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private  UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<MyUser> getUsers() {
        return userRepository.findAll();
    }


    @PostMapping
    public MyUser createUser(@RequestBody MyUser user) {
        return userRepository.save(user);
    }
    @PutMapping("/{id}")
    public MyUser updateUser(@PathVariable Long id, @RequestBody MyUser myUserDetails) {
        MyUser myUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        myUser.setName(myUserDetails.getName());
        myUser.setAge(myUserDetails.getAge());
        return userRepository.save(myUser);
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Long id) {
        MyUser myUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        userRepository.delete(myUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/{id}")
    public MyUser getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

}
