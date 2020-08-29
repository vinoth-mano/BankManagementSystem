package com.bank.msb;

import com.bank.msb.model.Loan;
import com.bank.msb.model.User;
import com.bank.msb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
@AutoConfigureWebTestClient
public class UserControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;
    List<User> customerList = Arrays.asList(
            new User("10","vin","vinione","123","1234","tn","india","vin@gmail.com","as122","7899977","25/07/1998","saving"));
    @Test
    public void registerUserTest(){
        User newUser = new User("11","vinothini","vinothini","123","1234","tn","india","vin@gmail.com","as122","7899977","25/07/1998","saving");
        webTestClient.post().uri("/user/registration").contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newUser), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.name").isEqualTo("vinothini");

    }
    @Test
    public void userLoginTest(){
        User newUser = new User("11","vinothini","vinothini","123","1234","tn","india","vin@gmail.com","as122","7899977","25/07/1998","saving");
        webTestClient.post().uri("/user/login").contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newUser), User.class)
                .exchange()
                .expectStatus().isFound();
    }
    @Test
    public void createLoanest(){
        Loan newLoan = new Loan("302","personal","10000","25/07/2020","10","10");
        webTestClient.post().uri("/user/loan").contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newLoan), Loan.class)
                .exchange()
                .expectStatus().isCreated();

    }
    @Test
    public void updateByIdTest(){
        User cust = new  User("11","vinothini","vinothini","123","madurai","tn","india","vin@gmail.com","as122","7899977","25/07/1998","saving");
        webTestClient.put().uri("/user/update//{userId}", "11")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(cust), User.class)
                .exchange()
                .expectStatus().isCreated();

    }


}
