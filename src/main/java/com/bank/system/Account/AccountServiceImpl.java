package com.bank.system.Account;

import com.bank.system.Account.Models.Account;
import com.bank.system.Bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final BankService bankService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, BankService bankService){
        this.accountRepository = accountRepository;
        this.bankService = bankService;
    }


    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        Timestamp createdAt = Timestamp.from(Instant.now());

        if(account.getCreatedAt() == null)
            account.setCreatedAt(createdAt);

        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByAccountId(Long accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    @Override
    public void deteleById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account register(Account account, Long id) {
        if(id != null && bankService.findById(id).isPresent() && account.getFullName() != null){
            account.setBank(bankService.findById(id).get());
            return save(account);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Account withdraw(Account account, double amount, double feeAmount) {
        if(account.getAccountBalance() < amount + feeAmount)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        double balance = account.getAccountBalance() - (amount+feeAmount);

        account.setAccountBalance(BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP).doubleValue());

        Timestamp updatedAt = Timestamp.from(Instant.now());
        account.setUpdatedAt(updatedAt);

        return accountRepository.save(account);
    }

    @Override
    public Account deposit(Account account, double amount) {
        if(amount > 0) {
            double balance = account.getAccountBalance() + amount;
            account.setAccountBalance(BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP).doubleValue());

            Timestamp updatedAt = Timestamp.from(Instant.now());
            account.setUpdatedAt(updatedAt);

            return accountRepository.save(account);
        }

        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }
}
