DROP TABLE IF EXISTS Transactions, StockInvestments, RealEstateInvestments, Investments, CheckingAccounts, SavingsAccounts, Accounts CASCADE ;




CREATE DATABASE bankingdb;
USE bankingdb;



CREATE TABLE Accounts (
                          accountNumber VARCHAR(20) PRIMARY KEY,
                          accountHolder VARCHAR(100) NOT NULL,
                          balance DECIMAL(15, 2) NOT NULL
);

CREATE TABLE SavingsAccounts (
                                 accountNumber VARCHAR(20) PRIMARY KEY,
                                 interestRate DECIMAL(5, 2),
                                 minimumBalance DECIMAL(15, 2),
                                 penalty DECIMAL(15, 2),
                                 FOREIGN KEY (accountNumber) REFERENCES Accounts(accountNumber)
);

CREATE TABLE CheckingAccounts (
                                  accountNumber VARCHAR(20) PRIMARY KEY,
                                  overdraftLimit DECIMAL(15, 2),
                                  transactionFee DECIMAL(15, 2),
                                  debitCardNumber VARCHAR(20),
                                  FOREIGN KEY (accountNumber) REFERENCES Accounts(accountNumber)
);

CREATE TABLE Investments (
                             investmentId VARCHAR(20) PRIMARY KEY,
                             investmentName VARCHAR(100) NOT NULL,
                             investmentValue DECIMAL(15, 2) NOT NULL
);

CREATE TABLE RealEstateInvestments (
                                       investmentId VARCHAR(20) PRIMARY KEY,
                                       surface DECIMAL(15, 2),
                                       pricePerSquareMeter DECIMAL(15, 2),
                                       address VARCHAR(255),
                                       propertyType VARCHAR(50),
                                       annualYield VARCHAR(10),
                                       FOREIGN KEY (investmentId) REFERENCES Investments(investmentId)
);

CREATE TABLE StockInvestments (
                                  investmentId VARCHAR(20) PRIMARY KEY,
                                  numberOfStocks INT,
                                  stockPrice DECIMAL(15, 2),
                                  stockSymbol VARCHAR(10),
                                  FOREIGN KEY (investmentId) REFERENCES Investments(investmentId)
);

CREATE TABLE Transactions (
                              transactionId VARCHAR(255) PRIMARY KEY,
                              receiver VARCHAR(255) NOT NULL,
                              sender VARCHAR(255) NOT NULL,
                              amount DECIMAL(15, 2) NOT NULL
);


select * from Accounts;
select * from SavingsAccounts;
select * from CheckingAccounts;
select * from Investments;
select * from RealEstateInvestments;
select * from StockInvestments;
select * from Transactions;
