package com.academy.data;

import java.util.Objects;

public class Account {

    private int userId;
    private int balance;
    private String currency;


    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(userId, balance, currency);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
