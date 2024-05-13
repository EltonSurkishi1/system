package com.bank.system.Bank;

import com.bank.system.Account.Models.Account;
import com.bank.system.Bank.Models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public Iterable<Bank> findAll(){
        return bankService.findAll();
    }

    @PostMapping(path = "/register")
    public Bank addBank(@RequestBody Bank bank){
        return bankService.save(bank);
    }

    @GetMapping(path = "/{id}")
    public Optional<Bank> getBankById(@PathVariable Long id){
        return bankService.findById(id);
    }
}


