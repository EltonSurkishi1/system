package com.bank.system.Bank;

import com.bank.system.Bank.Models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    @Override
    public Iterable<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank save(Bank bank) {
        Timestamp createdAt = Timestamp.from(Instant.now());

        if(bank.getCreatedAt() == null)
            bank.setCreatedAt(createdAt);
        else
            bank.setUpdatedAt(createdAt);

        return bankRepository.save(bank);
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return bankRepository.findById(id);
    }
}
