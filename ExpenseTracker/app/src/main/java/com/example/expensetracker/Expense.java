package com.example.expensetracker;

public class Expense {
    public String name;
    public float amount;

    public Expense(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public Expense(String name, float amount, long timestamp) {
        this.name = name;
        this.amount = amount;
    }
}
