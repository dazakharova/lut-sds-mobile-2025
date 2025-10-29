package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpenseAdapter extends BaseAdapter {

    public interface OnExpenseDeleteListener {
        void onDelete(int position, Expense expense);
    }
    LayoutInflater mInflater;
    List<Expense> data;
    private final OnExpenseDeleteListener deleteListener;

    public ExpenseAdapter(Context c, List<Expense> data, OnExpenseDeleteListener deleteListener) {
        this.data = data;
        this.deleteListener = deleteListener;
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
        View deleteBtn = v.findViewById(R.id.btnDelete);

        Expense expense = data.get(i);
        expenseNameTextView.setText(expense.name);
        expenseAmountTextView.setText(String.format("€%.2f", expense.amount));
        categoryTextView.setText(expense.category);

        deleteBtn.setOnClickListener(v1 -> {
            if (deleteListener != null) {
                deleteListener.onDelete(i, expense);
            }
        });

        return v;
    }
}
