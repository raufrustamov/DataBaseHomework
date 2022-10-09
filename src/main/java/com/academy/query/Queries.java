package com.academy.query;

import com.academy.data.Account;
import com.academy.data.Amount;
import com.academy.data.User;

import java.sql.*;
import java.util.HashMap;

import static java.lang.String.format;

public class Queries {
    public static HashMap<Integer, String> map = new HashMap<>();
    public static HashMap<Integer, Integer> balance = new HashMap<>();

    public static void addAccountForExistingUser(Connection connection, Account account) throws SQLException, NullPointerException {
        if (isCurrencyExists(connection, account.getUserId(), account.getCurrency())) {
            System.out.println("Currency exist");
        } else {
            insertNewAccount(connection, account);
        }
    }

    private static boolean isCurrencyExists(Connection connection, int userId, String currency) throws SQLException {


        try (PreparedStatement statement = connection.prepareStatement("SELECT  currency FROM Accounts WHERE userId = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (currency.equalsIgnoreCase(resultSet.getString("currency"))) return true;
            }
            return false;
        }

    }

    private static void insertNewAccount(Connection connection, Account account) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(format("INSERT INTO Accounts(userId,balance,currency) " +
                "VALUES(%d,'%d','%s')", account.getUserId(), account.getBalance(), account.getCurrency()))) {
            statement.executeUpdate();
        }
    }

    public static void addTransaction(Connection connection, Amount amount) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(format("INSERT INTO Transactions(accountId,amount) VALUES(%d,'%d')",
                amount.getAccountId(), amount.getTransactionAmount()))) {
            statement.executeUpdate();
        }
    }

    public static void updateAccountBalance(Connection connection, Amount amount, int newBalance) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(format("UPDATE Accounts SET balance=%d WHERE accountID=%d ",
                        newBalance, amount.getAccountId()))) {
            statement.executeUpdate();
        }
    }


    public static void addNewUser(Connection connection, User user) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format("INSERT INTO Users(name,address) VALUES('%s','%s')",
                user.getName(), user.getAddress()));
        statement.close();

    }

    public static HashMap getUserId(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM Users");
        while (result.next()) {
            map.put(result.getInt("userId"), result.getString("name"));
        }
        return map;
    }

    public static HashMap getAccountCurrency(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM Accounts");
        while (result.next()) {
            map.put(result.getInt("userId"), result.getString("currency"));
        }
        return map;
    }

    public static HashMap getAccountId(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM Accounts");
        while (result.next()) {
            map.put(result.getInt("accountId"), result.getString("currency"));
        }
        return map;
    }

    public static HashMap getAccountBalance(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM Accounts");
        while (result.next()) {
            balance.put(result.getInt("accountId"), result.getInt("balance"));
        }
        return balance;
    }


}





