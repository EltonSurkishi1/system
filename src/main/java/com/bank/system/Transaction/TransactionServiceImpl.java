package com.bank.system.Transaction;

import com.bank.system.Account.AccountService;
import com.bank.system.Account.Models.Account;
import com.bank.system.Bank.BankService;
import com.bank.system.Bank.Models.Bank;
import com.bank.system.Transaction.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    private final BankService bankService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, BankService bankService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.bankService = bankService;
    }


    @Override
    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        Timestamp createdAt = Timestamp.from(Instant.now());

        if(transaction.getCreatedAt() == null)
            transaction.setCreatedAt(createdAt);

        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction register(Transaction transaction) {
        if(transaction.getAmount() < 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        if(transaction.getOriginatingAccountId() == null || transaction.getResultingAccountId() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Account originatingAccount = accountService.findByAccountId(transaction.getOriginatingAccountId());
        Account resultingAccount = accountService.findByAccountId(transaction.getResultingAccountId());

        if(originatingAccount != null && resultingAccount != null){
            if(originatingAccount.getBank().getId().equals(resultingAccount.getBank().getId())){
                double transactionFee = originatingAccount.getBank().getTransactionFlatFeeAmount().doubleValue();
                accountService.withdraw(originatingAccount, transaction.getAmount(), transactionFee);
                accountService.deposit(resultingAccount, transaction.getAmount());
                transaction.setFeeAmount(transactionFee);

                setBankTransactionFee(originatingAccount, transactionFee, transaction.getAmount());
                setResultingBankTotalAmount(resultingAccount, transaction.getAmount());
                return save(transaction);
            }

            double transactionFee = transaction.getAmount() * originatingAccount.getBank().getTransactionPercentFeeValue().doubleValue() / 100;
            accountService.withdraw(originatingAccount, transaction.getAmount(), transactionFee);
            accountService.deposit(resultingAccount, transaction.getAmount());
            transaction.setFeeAmount(transactionFee);

            setBankTransactionFee(originatingAccount, transactionFee, transaction.getAmount());
            setResultingBankTotalAmount(resultingAccount, transaction.getAmount());
            return save(transaction);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Iterable<Transaction> getTransactionsByNumber(Long accountId) {
        if (accountId == null)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);

        List<Transaction> transactions = (List<Transaction>)findAll();

        if (transactions != null){
           return transactions.stream().filter(it -> it.getOriginatingAccountId().equals(accountId) || it.getResultingAccountId().equals(accountId)).collect(Collectors.toList());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private void setBankTransactionFee(Account originatingAccount, double transactionFee, double amount){
        Optional<Bank> optionalOriginatingBank = bankService.findById(originatingAccount.getBank().getId());

        if (optionalOriginatingBank.isPresent()) {
            Bank bank = optionalOriginatingBank.get();
            BigDecimal feeAmount = bank.getTotalTransactionFeeAmount();

            if(feeAmount != null){
                BigDecimal totalFee = BigDecimal.valueOf(feeAmount.doubleValue() + transactionFee).setScale(2, RoundingMode.HALF_UP);
                bank.setTotalTransactionFeeAmount(totalFee);
            }else{
                bank.setTotalTransactionFeeAmount(BigDecimal.valueOf(transactionFee).setScale(2, RoundingMode.HALF_UP));
            }


            BigDecimal bankAmount = bank.getTotalTransferAmount();
            BigDecimal totalAmount = null;

            if (bankAmount != null)
                 totalAmount = BigDecimal.valueOf(bankAmount.doubleValue() + amount).setScale(2, RoundingMode.HALF_UP);
            else
                totalAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);

            bank.setTotalTransferAmount(totalAmount);

            bankService.save(bank);
        }
    }

    private void setResultingBankTotalAmount(Account resultingAccount, double amount){
        Optional<Bank> optionalResultingBank = bankService.findById(resultingAccount.getBank().getId());

        if (optionalResultingBank.isPresent()) {
            Bank bank = optionalResultingBank.get();
            BigDecimal bankAmount = bank.getTotalTransferAmount();
            BigDecimal totalAmount = null;

            if (bankAmount != null)
                totalAmount = BigDecimal.valueOf(bankAmount.doubleValue() + amount).setScale(2, RoundingMode.HALF_UP);
            else
                totalAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);

            bank.setTotalTransferAmount(totalAmount);

            bankService.save(bank);
        }
    }


}
