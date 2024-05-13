package com.bank.system.Account.Models;

import com.bank.system.Bank.Models.Bank;
import com.bank.system.Commons.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends BaseModel {

    @Column(unique = true)
    private Long accountId;

    private String fullName;

    private double accountBalance;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
