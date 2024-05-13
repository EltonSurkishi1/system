package com.bank.system.Bank.Models;

import com.bank.system.Account.Models.Account;
import com.bank.system.Commons.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "banks")
public class Bank extends BaseModel {

    private String bankName;

    @OneToMany(mappedBy = "bank")
    private Set<Account> accountSet;

    private BigDecimal totalTransactionFeeAmount;

    private BigDecimal totalTransferAmount;

    private BigDecimal transactionFlatFeeAmount;

    private BigDecimal transactionPercentFeeValue;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Set<Account> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<Account> accountSet) {
        this.accountSet = accountSet;
    }

    public BigDecimal getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(BigDecimal totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public BigDecimal getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(BigDecimal totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public BigDecimal getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(BigDecimal transactionFlatFeeAmount) {
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public BigDecimal getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(BigDecimal transactionPercentFeeValue) {
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }
}
