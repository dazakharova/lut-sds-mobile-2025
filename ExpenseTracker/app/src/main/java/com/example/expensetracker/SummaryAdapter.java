package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class SummaryAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<SummaryActivity.SummaryItem> items;

    public SummaryAdapter(Context ctx, List<SummaryActivity.SummaryItem> items) {
        this.inflater = LayoutInflater.from(ctx);
        this.items = items;
    }

    @Override public int getCount() {
        return items.size();
    }

    @Override public Object getItem(int i) {
        return items.get(i);
    }
    @Override public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) v = inflater.inflate(R.layout.row_summary, parent, false);

        TextView cat = v.findViewById(R.id.summaryCategory);
        TextView amt = v.findViewById(R.id.summaryAmount);
        ProgressBar bar = v.findViewById(R.id.summaryBar);

        SummaryActivity.SummaryItem it = items.get(position);
        cat.setText(it.category);
        amt.setText(String.format(Locale.getDefault(), "€%.2f (%.0f%%)", it.amount, it.percent));
        bar.setProgress(Math.round(it.percent));

        return v;
    }
}
