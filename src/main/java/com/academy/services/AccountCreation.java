package com.academy.services;

import com.academy.data.Account;

import java.util.Scanner;

public class AccountCreation {
    public static Account newAccount() {
        Account account = new Account();
        Scanner console = new Scanner(System.in);
        System.out.println("Please input your ID: ");
        account.setUserId(Integer.parseInt(console.nextLine()));
        System.out.println("Please select your currency: ");
        account.setCurrency(console.nextLine());
        account.setBalance(0);
        return account;
    }
}
