package com.bank.system.Account;

import com.bank.system.Account.Models.Account;

import java.util.Optional;

public interface AccountService {
    Iterable<Account> findAll();

    Account save(Account account);

    Optional<Account> findById(Long id);

    Account findByAccountId(Long accountId);

    void deteleById(Long id);

    Account register(Account account, Long id);

    Account withdraw(Account account,double amount, double feeAmount);

    Account deposit(Account account,double amount);
}
