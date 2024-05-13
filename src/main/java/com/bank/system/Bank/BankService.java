package com.bank.system.Bank;

import com.bank.system.Account.Models.Account;
import com.bank.system.Bank.Models.Bank;

import java.util.Optional;

public interface BankService {
    Iterable<Bank> findAll();

    Bank save(Bank bank);

    Optional<Bank> findById(Long id);
}
