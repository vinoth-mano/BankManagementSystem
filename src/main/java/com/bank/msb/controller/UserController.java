package com.bank.msb.controller;

import com.bank.msb.model.Loan;
import com.bank.msb.model.User;
import com.bank.msb.repository.LoanRepository;
import com.bank.msb.repository.UserRepository;
import com.bank.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/user/registration")
    public Mono<ResponseEntity<User>> registerUser(@RequestBody User user) {
        // return userRepository.save(user).map(createdUser ->  new ResponseEntity<>(createdUser, HttpStatus.CREATED));
        return userService.createUser(user, userRepository);
    }

    @PostMapping("/user/login")
    public Mono<ResponseEntity<String>> userLogin(@RequestBody User user) {
        return userService.userValidation(user, userRepository);

    }

    @PostMapping("/user/loan")
    public Mono<ResponseEntity<Loan>> createLoan(@RequestBody Loan loan) {
        return userService.createLoan(loan, loanRepository);
    }

    @PutMapping("/user/update/{userID}")
    public Mono<ResponseEntity<String>> updateById(@PathVariable("userID") String userID, @RequestBody User user) {

        return userService.updateUserAccount(userID, user, userRepository);
    }

    @GetMapping("/user/fetch/{userID}")
    public Mono<User> updateById(@PathVariable("userID") String userID) {
        return userService.getUserDetails(userID, userRepository);
    }
}