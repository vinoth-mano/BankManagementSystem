package com.bank.msb.controller;

import com.bank.msb.model.User;
import com.bank.msb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/registration")
    public Mono<ResponseEntity<User>> registerUser(@RequestBody User user){
        return userRepository.save(user).map(createdUser ->  new ResponseEntity<>(createdUser, HttpStatus.CREATED));
    }

    @PostMapping("/user/login")
    public Mono<ResponseEntity<String>> userLogin(@RequestBody User user){
        return userRepository.findByUserIDAndPassword(user.getUserID(),user.getPassword())
                .map(loggedInUser-> new ResponseEntity<>("Login Successfully", HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>("Login failed", HttpStatus.NOT_FOUND));
    }
}
