package com.academy;

import com.academy.data.Account;
import com.academy.data.Amount;
import com.academy.data.User;
import com.academy.query.Queries;
import com.academy.services.AccountCreation;
import com.academy.services.TransactionAmount;
import com.academy.services.UserCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import static com.academy.query.Queries.*;

public class ApplicationDB {

    private static final String JDBC_PATH = "org.sqlite.JDBC";
    public static final String ERROR_PATH = "Driver not find";
    public static final String DB_PATH = "jdbc:sqlite:test.db";
    public static final String DB_ERROR = "Database not found!";
    public static HashMap<Integer, String> users = new HashMap<>();
    public static HashMap<String, Integer> accountCurrency = new HashMap<>();
    public static HashMap<Integer, String> accounts = new HashMap<>();
    public static HashMap<Integer, Integer> balance = new HashMap<>();


    public static void main(String[] args) {

        if (checkDriver()) {
            try {
                Connection connection = DriverManager.getConnection(DB_PATH);
                String operation;
                do {
                    menu();
                    Scanner console = new Scanner(System.in);
                    operation = console.nextLine();
                    switch (operation) {
                        case "1":
                            User user = UserCreation.newUser();
                            addNewUser(connection, user);
                            System.out.println("Information added to database");
                            break;
                        case "2":
                            users = Queries.getUserId(connection);
                            accountCurrency = Queries.getAccountCurrency(connection);
                            Account account = AccountCreation.newAccount();

                            if (users.containsKey(account.getUserId())) {
                                addAccountForExistingUser(connection, account);
                            } else {
                                System.out.println("User with this ID doesn't  exist");
                            }
                            break;
                        case "3":
                            accounts = Queries.getAccountId(connection);
                            balance = Queries.getAccountBalance(connection);
                            Amount amount = TransactionAmount.amount();
                            try {
                                if (updateAccountBalanceAfterOperation(connection, amount)) break;
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Please change amount");
                            }
                            break;
                        case "4":
                            System.out.println("Exit from program");
                    }

                } while (!operation.equals("5"));

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(DB_ERROR);
            }
        }
    }

    private static boolean updateAccountBalanceAfterOperation(Connection connection, Amount amount) throws SQLException {
        int newBalance;
        if (TransactionAmount.checkAmount(amount.getTransactionAmount()) && amount.getOperation().equals("deposit")) {
            newBalance = (int) (FindAccountBalance(connection, amount) + amount.getTransactionAmount());
            updateAccountBalance(connection, amount, newBalance);
            addTransaction(connection, amount);
        } else if (TransactionAmount.checkAmount(amount.getTransactionAmount()) && amount.getOperation().equals("withdrawal")) {
            newBalance = (int) (FindAccountBalance(connection, amount) - amount.getTransactionAmount());
            updateAccountBalance(connection, amount, newBalance);
            addTransaction(connection, amount);
        } else {
            return true;
        }
        return false;
    }

    private static long FindAccountBalance(Connection connection, Amount amount) throws SQLException {
        HashMap<Integer, Integer> accountBalance = getAccountBalance(connection);
        return accountBalance.get(amount.getAccountId());
    }

    public static void menu() {
        System.out.println("Please input operation:\n\n" + "1.Register new user\n\n2.Add account for existing user\n\n"
                + "3.Operations with amount \n\n"
                + "4.Exit");
    }

    public static boolean checkDriver() {
        try {
            Class.forName(JDBC_PATH);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(ERROR_PATH);
            return false;
        }
    }


}
