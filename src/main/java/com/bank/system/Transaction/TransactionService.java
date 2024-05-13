package com.bank.system.Transaction;

import com.bank.system.Transaction.Models.Transaction;

import java.util.Optional;

public interface TransactionService {
    Iterable<Transaction> findAll();

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);

    Transaction register(Transaction transaction);

    Iterable<Transaction> getTransactionsByNumber(Long accountId);
}
