package com.schrader.mariadbtest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MainController {
    private UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping()
    ResponseEntity<User> updateUser(@RequestBody User user) {
        //return (userRepository.existsById(Long.valueOf((id))))
        return (userRepository.existsById(user.getId()))
                ? new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK)
                : new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}
