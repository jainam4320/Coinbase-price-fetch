package com.example.coinbase;

public class Transaction {
    public int id;
    public String name;
    public String amount;



    Transaction() {

    }

    public Transaction(int id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

}
