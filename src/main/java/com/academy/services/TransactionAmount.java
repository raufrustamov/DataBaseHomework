package com.academy.services;

import com.academy.data.Amount;

import java.util.Scanner;

public class TransactionAmount {
    public static String operation;
    public static final long LIMIT = 100000000;
    public static final String MESSAGE_TO_INPUT = "Please input amount";

    public static Amount amount() {
        operations();
        Amount amount = new Amount();
        Scanner console = new Scanner(System.in);
        operation = console.nextLine();
        switch (operation) {
            case "1":
                amount.setOperation("deposit");
                System.out.println(MESSAGE_TO_INPUT);
                amountSet(amount, console);
                accountSet(amount, console);
                break;
            case "2":
                amount.setOperation("withdrawal");
                System.out.println(MESSAGE_TO_INPUT);
                amountSet(amount, console);
                accountSet(amount, console);
                break;
        }
        return amount;
    }

    private static void amountSet(Amount amount, Scanner console) {
        amount.setTransactionAmount(console.nextLong());
    }

    public static void accountSet(Amount amount, Scanner console) {
        System.out.println("Please enter your account id: ");
        int account = console.nextInt();
        amount.setAccountId(account);
    }

    public static void operations() {
        System.out.println("Please select operation\n1.Deposit\n2.Withdrawal");
    }

    public static boolean checkAmount(long amount) {
        if (amount > LIMIT) {
            System.out.println("You have exceeded the transaction limit");
            return false;
        } else {
            return true;
        }
    }


}
