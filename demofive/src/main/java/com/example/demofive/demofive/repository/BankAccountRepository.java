package com.example.demofive.demofive.repository;

import com.example.demofive.demofive.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    // Additional query methods can be defined here if needed
    // For example, to find a bank account by bank name:
    // Optional<BankAccount> findByBankName(String bankName);
}
