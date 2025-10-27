package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpenseAdapter extends BaseAdapter {
    LayoutInflater mInflater;

    List<Expense> data;

    public ExpenseAdapter(Context c, List<Expense> data) {
        this.data = data;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.row_expense, null);

        TextView expenseNameTextView = v.findViewById(R.id.expenseName);
        TextView expenseAmountTextView = v.findViewById(R.id.expenseAmount);
        TextView categoryTextView = v.findViewById(R.id.expenseCategory);

        Expense expense = data.get(i);
        expenseNameTextView.setText(expense.name);
        expenseAmountTextView.setText(String.format("€%.2f", expense.amount));
        categoryTextView.setText(expense.category);

        return v;
    }
}
