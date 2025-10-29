package com.example.expensetracker;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SummaryActivity extends AppCompatActivity {

    static class SummaryItem {
        final String category;
        final float amount;
        final float percent;
        SummaryItem(String c, float a, float p) {
            category = c;
            amount = a;
            percent = p;
        }
    }

    private final List<SummaryItem> items = new ArrayList<>();
    private SummaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summaryRoot), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        MaterialToolbar tb = findViewById(R.id.toolbar);
        tb.setNavigationOnClickListener(v -> finish());

        TextView totalText = findViewById(R.id.totalTextSummary);
        ListView list = findViewById(R.id.summaryList);

        ArrayList<String> cats = getIntent().getStringArrayListExtra("extra_cats");
        @SuppressWarnings("unchecked")
        ArrayList<Float> vals = (ArrayList<Float>) getIntent().getSerializableExtra("extra_vals");
        float grand = getIntent().getFloatExtra("extra_grand", 0f);

        if (cats != null && vals != null && cats.size() == vals.size() && grand > 0f) {
            for (int i = 0; i < cats.size(); i++) {
                float amount = vals.get(i);
                float percent = (amount / grand) * 100f;
                items.add(new SummaryItem(cats.get(i), amount, percent));
            }
            // sort by amount
            Collections.sort(items, Comparator.comparingDouble(i -> -i.amount));
        }

        adapter = new SummaryAdapter(this, items);
        list.setAdapter(adapter);

        totalText.setText(String.format(Locale.getDefault(),
                getString(R.string.summary_total_fmt), grand));
    }
}
