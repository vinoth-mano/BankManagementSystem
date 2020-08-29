package com.bank.msb;

import com.bank.msb.model.Loan;
import com.bank.msb.model.User;
import com.bank.msb.repository.LoanRepository;
import com.bank.msb.repository.UserRepository;
import com.bank.msb.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

@Test
    public void createUserTest(){
    User cust = new  User("12","vinothini","vinothini","123","madurai","tn","india","vin@gmail.com","as122","7899977","25/07/1998","saving");
    Mono<User> createdUser=userRepository.save(cust);
    StepVerifier.create(createdUser).expectSubscription().expectNextMatches(user->user.getUserID().equals("12")).verifyComplete();
}
@Test
    public void userValidationTest() {
    User cust = new User("13", "vinothini", "vinothini", "123", "madurai", "tn", "india", "vin@gmail.com", "as122", "7899977", "25/07/1998", "saving");
    Mono<User> findUser = userRepository.findByUserIDAndPassword("12", "123");
    StepVerifier.create(findUser).expectNextCount(1).verifyComplete();
}
@Test
    public void createLoanTest(){
    Loan newLoan = new Loan("3032","personal","10000","25/07/2020","10","10");
    Mono<Loan> createdLoan=loanRepository.save(newLoan);
    StepVerifier.create(createdLoan).expectSubscription() .expectNextMatches(ln->ln.getLoanId().equals("3032")).verifyComplete();
}
@Test
    public void updateUserAccountTest(){
    Mono<User> findUser = userRepository.findByUserIDAndPassword("12", "123")
            .map(us->{
                us.setName("vinoth");
                return us;
            })
            .flatMap(usr->{
                return userRepository.save(usr);
            });
}

}
