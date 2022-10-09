package com.academy.data;

public class Amount {
    public long transactionAmount;
    public int accountId;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String operation;

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public long setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
        return transactionAmount;
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


}
