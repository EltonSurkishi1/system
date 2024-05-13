package com.bank.system.Account;

import com.bank.system.Account.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Iterable<Account> findAll(){
        return accountService.findAll();
    }

    @PostMapping(path = "/register/{id}")
    public Account addUser(@RequestBody Account account, @PathVariable Long id){
        return accountService.register(account, id);
    }

    @GetMapping(path = "/{id}")
    public Optional<Account> getUserById(@PathVariable Long id){
        return accountService.findById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        accountService.deteleById(id);
    }
}
