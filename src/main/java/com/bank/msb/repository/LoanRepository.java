package com.bank.msb.repository;

import com.bank.msb.model.Loan;
import com.bank.msb.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LoanRepository extends ReactiveMongoRepository<Loan,String> {

}
