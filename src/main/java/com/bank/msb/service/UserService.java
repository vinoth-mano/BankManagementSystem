package com.bank.msb.service;

import com.bank.msb.model.Loan;
import com.bank.msb.model.User;
import com.bank.msb.repository.LoanRepository;
import com.bank.msb.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    public Mono<ResponseEntity<User>> createUser(User user, UserRepository userRepository) {
        return userRepository.save(user).map(createdUser ->  new ResponseEntity<>(createdUser, HttpStatus.CREATED));
    }

    public Mono<ResponseEntity<String>> userValidation(User user, UserRepository userRepository) {
        return userRepository.findByUserIDAndPassword(user.getUserID(),user.getPassword())
                .map(loggedInUser-> new ResponseEntity<>("Login Successfully", HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>("Login failed", HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Loan>> createLoan(Loan loan, LoanRepository loanRepository) {
        return loanRepository.save(loan).map(createdUser ->  new ResponseEntity<>(createdUser, HttpStatus.CREATED));
    }

    public Mono<ResponseEntity<String>> updateUserAccount(String userID, User user, UserRepository userRepository) {

        return userRepository.findById(userID).map(existingUser -> existingUser.toBuilder().
         address(user.getAddress()).emailAddress(user.getEmailAddress()).
               contactNo(user.getContactNo()).build()).flatMap(userRepository::save).map(updateUser ->  new ResponseEntity<>("User Account Updated successfully", HttpStatus.CREATED)).
              defaultIfEmpty(new ResponseEntity<>("User Account Updated", HttpStatus.NOT_FOUND));
    }

    public Mono<User> getUserDetails(String userID, UserRepository userRepository) {
            return userRepository.findById(userID);
        }

}
