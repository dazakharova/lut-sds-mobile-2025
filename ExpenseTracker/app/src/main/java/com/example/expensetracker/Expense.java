package com.example.expensetracker;

public class Expense {
    public String name;
    public float amount;
    public String category;

    public Expense(String name, float amount) {
        this(name, amount, "Other");
    }

    public Expense(String name, float amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = (category == null || category.isEmpty()) ? "Other" : category;
    }
}
