package com.github.nightfuze;

import java.time.LocalDateTime;
import java.util.Random;

public class BankAccount {
    private static final Random RANDOM = new Random();
    private static final int NUMBER_SIZE = 8;

    private final String name;
    private final LocalDateTime openDate;
    private final String number;
    private int balance;
    private boolean isBlocked;

    BankAccount(String ownerName) {
        this.name = ownerName;
        this.balance = 0;
        this.openDate = LocalDateTime.now();
        this.isBlocked = false;
        this.number = generateNumber(NUMBER_SIZE);
    }

    public static String generateNumber(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int randomDigit = RANDOM.nextInt(10);
            sb.append(randomDigit);
        }
        return sb.toString();
    }

    public boolean deposit(int amount) {
        if (isBlocked()) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }

        this.balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (isBlocked()) {
            return false;
        }
        if (amount > getBalance()) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (this.equals(otherAccount)) {
            return false;
        }
        if (this.isBlocked() || otherAccount.isBlocked()) {
            return false;
        }

        if (this.withdraw(amount)) {
            if (otherAccount.deposit(amount)) {
                return true;
            }
            this.deposit(amount);
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        this.isBlocked = true;
    }

    public void unblock() {
        this.isBlocked = false;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount account = (BankAccount) o;
        return number.equals(account.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", openDate=" + openDate +
                ", isBlocked=" + isBlocked +
                ", number='" + number + '\'' +
                '}';
    }
}
