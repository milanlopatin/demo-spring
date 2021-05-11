package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.projections.UserProjection;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping(value = "user")
    public User getUser(@RequestParam(name = "id") UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "user_by_passport")
    public User getUserByPassport(@RequestParam(name = "passport") String passport) {
        return userRepository.findByPassportNumber(passport);
    }

    @GetMapping(value = "projection_by_passport")
    public UserProjection getProjectionByPassport(@RequestParam(name = "passport") String passport) {
        return userRepository.findByPassportNumberIs(passport);
    }

    @GetMapping(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "add_user")
    public User addUser(@RequestParam(name = "passport", required = false) String passportNumber,
                        @RequestParam(name = "name", required = false) String name) {
        User user = new User();
        user.setFirstName(name);
        user.setPassportNumber(passportNumber);
        userRepository.save(user);
        return user;
    }
}
