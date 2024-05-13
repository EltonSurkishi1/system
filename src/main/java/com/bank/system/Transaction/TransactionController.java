package com.bank.system.Transaction;

import com.bank.system.Account.AccountService;
import com.bank.system.Transaction.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Iterable<Transaction> getTransactions(){
        return transactionService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @PostMapping(path = "/register")
    public Transaction registerNewTransaction(@RequestBody Transaction transaction){
        return transactionService.register(transaction);
    }

    @GetMapping(path = "accounts/{id}")
    public Iterable<Transaction> getTransactionsByAccountNumber(@PathVariable Long id){
        return transactionService.getTransactionsByNumber(id);
    }
}
