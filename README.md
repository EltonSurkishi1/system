# Bank System Java Console Application

This Java Restful API system is designed to simulate a bank system, allowing users to manage accounts and perform transactions. The application is built using object-oriented programming principles and includes features such as account creation, transaction processing, balance checks, and more.

# Table of contents
- Features
- Technologies Used
- Project Structure
- Instructions


# Features
- CRUD Operations: Perform Create, Read, Update, and Delete operations on your data (update & delete not fully included because it was not on requirements!)
- Database Integration: Utilize the power of PostgreSQL for storing and managing your data.
- Spring Boot: Leverage the Spring Boot framework for rapid application development.
- RESTful API: Create RESTful APIs to interact with your application programmatically.
- Create a bank with all required values.
- Create accounts with unique IDs and manage user details.
- Perform transactions, including flat fee and percent fee transfers between accounts.
- Withdraw and deposit money to/from accounts.
- View transaction history for any account.
- Check account balances and list all bank accounts.
- Track total transaction fee and transfer amounts for the bank.

# Technologies Used
- Java 8
- Spring Boot 2.6.4
- PostgreSQL 16.2
- PgAdmin 4

# Project structure

```markdown
.
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
├── .gradle
│   ├── file-system.probe
│   └── 8.7
│       ├── gc.properties
│       └── checksums
│           └── checksums.lock
│   ├── dependencies-accessors
│   │   └── gc.properties
│   ├── executionHistory
│   │   ├── executionHistory.bin
│   │   └── executionHistory.lock
│   ├── expanded
│   ├── fileChanges
│   │   └── last-build.bin
│   ├── fileHashes
│   │   ├── fileHashes.bin
│   │   ├── fileHashes.lock
│   │   └── resourceHashesCache.bin
│   └── vcsMetadata
│       └── gc.properties
├── buildOutputCleanup
│   ├── buildOutputCleanup.lock
│   ├── cache.properties
│   └── outputFiles.bin
├── vcs-1
│   └── gc.properties
├── build
│   └── classes
│       └── java
│           └── main
│               └── com
│                   └── bank
│                       └── system
│                           ├── SystemApplication.class
│                           ├── Account
│                           │   ├── AccountController.class
│                           │   ├── AccountRepository.class
│                           │   ├── AccountService.class
│                           │   └── AccountServiceImpl.class
│                           ├── Models
│                           │   └── Account.class
│                           ├── Bank
│                           │   ├── BankController.class
│                           │   ├── BankRepository.class
│                           │   ├── BankService.class
│                           │   └── BankServiceImpl.class
│                           ├── Models
│                           │   └── Bank.class
│                           └── Transaction
│                               ├── TransactionController.class
│                               ├── TransactionRepository.class
│                               ├── TransactionService.class
│                               └── TransactionServiceImpl.class
│                               └── Models
│                                   └── Transaction.class
├── generated
│   └── sources
│       └── annotationProcessor
│           └── java
│               └── main
├── resources
│   └── main
│       └── application.properties
└── tmp
    └── compileJava
        └── previous-compilation-data.bin
```

# Instructions

- git clone https://github.com/EltonSurkishi1/system.git
- load gradle dependencies
- Install PostgreeSql 16.2 with PgAdmin4
- Create a database and change table name username and password in properties
- Install Postman to test API calls

# Get Banks

- Request
  - GET http://localhost:8085/banks

- Response
 ```
  - [
    {
        "id": 2,
        "createdAt": "2024-05-12T12:51:30.931+00:00",
        "updatedAt": "2024-05-13T01:43:45.729+00:00",
        "bankName": "Raiffeisen Bank",
        "accountSet": [
            {
                "id": 6,
                "createdAt": "2024-05-12T07:42:48.895+00:00",
                "updatedAt": "2024-05-13T01:43:45.710+00:00",
                "accountId": 43556782,
                "fullName": "Elton Surkishi",
                "accountBalance": 15457.42
            }
        ],
        "totalTransactionFeeAmount": 29.49,
        "totalTransferAmount": 2417.00,
        "transactionFlatFeeAmount": 2.00,
        "transactionPercentFeeValue": 1.50
    },
    {
        "id": 1,
        "createdAt": "2024-05-12T11:52:51.847+00:00",
        "updatedAt": "2024-05-13T01:43:45.729+00:00",
        "bankName": "BKT",
        "accountSet": [
            {
                "id": 5,
                "createdAt": "2024-05-12T03:44:40.519+00:00",
                "updatedAt": "2024-05-12T04:16:37.127+00:00",
                "accountId": 532444534,
                "fullName": null,
                "accountBalance": 3575.5
            },
            {
                "id": 4,
                "createdAt": "2024-05-12T03:19:58.216+00:00",
                "updatedAt": "2024-05-13T01:43:45.728+00:00",
                "accountId": 53244534,
                "fullName": null,
                "accountBalance": 2316.6
            }
        ],
        "totalTransactionFeeAmount": 4.40,
        "totalTransferAmount": 2467.00,
        "transactionFlatFeeAmount": 2.50,
        "transactionPercentFeeValue": 2.00
    }
]
   ```

# Get Bank with id

- Request
  - GET http://localhost:8085/banks/1

- Response
 ```
{
    "id": 1,
    "createdAt": "2024-05-12T11:52:51.847+00:00",
    "updatedAt": "2024-05-13T01:43:45.729+00:00",
    "bankName": "BKT",
    "accountSet": [
        {
            "id": 5,
            "createdAt": "2024-05-12T03:44:40.519+00:00",
            "updatedAt": "2024-05-12T04:16:37.127+00:00",
            "accountId": 532444534,
            "fullName": null,
            "accountBalance": 3575.5
        },
        {
            "id": 4,
            "createdAt": "2024-05-12T03:19:58.216+00:00",
            "updatedAt": "2024-05-13T01:43:45.728+00:00",
            "accountId": 53244534,
            "fullName": null,
            "accountBalance": 2316.6
        }
    ],
    "totalTransactionFeeAmount": 4.40,
    "totalTransferAmount": 2467.00,
    "transactionFlatFeeAmount": 2.50,
    "transactionPercentFeeValue": 2.00
}
```
# Create Bank

- Request
  - POST http://localhost:8085/banks/register
```
  Content-Type: application/json
  
  {
     {
      "bankName": "Procredit",
      "totalTransactionFeeAmount": 0.00,
      "totalTransferAmount": 0.00,
      "transactionFlatFeeAmount": 2.50,
      "transactionPercentFeeValue": 3.00
  }
  }
```
- Response
 ```
{
    "id": 3,
    "createdAt": "2024-05-13T14:56:13.647+00:00",
    "updatedAt": null,
    "bankName": "Procredit",
    "accountSet": null,
    "totalTransactionFeeAmount": 0.00,
    "totalTransferAmount": 0.00,
    "transactionFlatFeeAmount": 2.50,
    "transactionPercentFeeValue": 3.00
}
```

# Create Account

- Request
  - POST http://localhost:8085/accounts/register/3
```
  Content-Type: application/json
  
     {
        "accountId": 23455263234,
        "fullName": "Elton Surkishi",
        "accountBalance": 14500.0
    }
```
- Response
 ```
    "id": 7,
    "createdAt": "2024-05-13T15:00:46.761+00:00",
    "updatedAt": null,
    "accountId": 23455263234,
    "fullName": "Elton Surkishi",
    "accountBalance": 14500.0
}
```

# Create Transaction 
- (withdrawal from originating and deposit to resulting account; both types of fees implemented based on wether it is transfers between same banks or not; updates banks total transfer amount and total fee)

- Request
  - POST http://localhost:8085/transactions/register
```
  Content-Type: application/json
  {
  "amount": 522.00,
  "originatingAccountId": 23455263234,
  "resultingAccountId": 53244534,
  "transactionReason": "Payment from Procredit bank"
}
```
- Response
 ```
{
    "id": 17,
    "createdAt": "2024-05-13T15:02:56.296+00:00",
    "updatedAt": null,
    "amount": 522.0,
    "originatingAccountId": 23455263234,
    "resultingAccountId": 53244534,
    "transactionReason": "Payment from Procredit bank",
    "feeAmount": 15.66
}
```
# Get Transactions of an account by accountIdNumber (withdrawal and deposit transactions) 

- Request
  - GET http://localhost:8085/transactions/accounts/23455263234

- Response
 ```
[
    {
        "id": 14,
        "createdAt": "2024-05-13T15:02:48.883+00:00",
        "updatedAt": null,
        "amount": 522.0,
        "originatingAccountId": 23455263234,
        "resultingAccountId": 53244534,
        "transactionReason": "Payment from Procredit bank",
        "feeAmount": 15.66
    },
    {
        "id": 15,
        "createdAt": "2024-05-13T15:02:53.357+00:00",
        "updatedAt": null,
        "amount": 522.0,
        "originatingAccountId": 23455263234,
        "resultingAccountId": 53244534,
        "transactionReason": "Payment from Procredit bank",
        "feeAmount": 15.66
    },
    {
        "id": 16,
        "createdAt": "2024-05-13T15:02:55.156+00:00",
        "updatedAt": null,
        "amount": 522.0,
        "originatingAccountId": 23455263234,
        "resultingAccountId": 53244534,
        "transactionReason": "Payment from Procredit bank",
        "feeAmount": 15.66
    },
    {
        "id": 17,
        "createdAt": "2024-05-13T15:02:56.296+00:00",
        "updatedAt": null,
        "amount": 522.0,
        "originatingAccountId": 23455263234,
        "resultingAccountId": 53244534,
        "transactionReason": "Payment from Procredit bank",
        "feeAmount": 15.66
    },
    {
        "id": 18,
        "createdAt": "2024-05-13T15:15:17.483+00:00",
        "updatedAt": null,
        "amount": 722.0,
        "originatingAccountId": 53244534,
        "resultingAccountId": 23455263234,
        "transactionReason": "Payment from Procredit bank",
        "feeAmount": 14.44
    }
]
```

# AccountBalance can be checked on account details. Same can be checked for banks, we get all accounts, totalamount, totalfeeamount and other properties.

