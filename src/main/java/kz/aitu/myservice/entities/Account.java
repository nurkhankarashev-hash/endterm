package kz.aitu.myservice.entities;

public class Account {
    private String accountNumber;
    private double balance;
    private int customerId;

    public Account() {
    }

    public Account(String accountNumber, double balance, int customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }


    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}