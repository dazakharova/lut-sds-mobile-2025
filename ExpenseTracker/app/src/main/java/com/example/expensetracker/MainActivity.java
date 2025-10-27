package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView totalText;
    private ListView expensesList;

    private final List<Expense> data = new ArrayList<>();
    private ExpenseAdapter adapter;

    // Receive result from AddExpenseActivity
    private final ActivityResultLauncher<Intent> addExpenseLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent dataIntent = result.getData();
                    String name = dataIntent.getStringExtra("extra_name");
                    float amount = dataIntent.getFloatExtra("extra_amount", 0f);
                    String category = dataIntent.getStringExtra("extra_category");
                    if (name != null && amount > 0f) {
                        data.add(new Expense(name, amount, category));
                        adapter.notifyDataSetChanged();
                        updateTotal();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        totalText = (TextView) findViewById(R.id.totalText);
        expensesList = (ListView) findViewById(R.id.expensesList);

        data.add(new Expense("Coffee", 2.5f, "Food"));
        data.add(new Expense("Groceries", 15.75f, "Food"));
        data.add(new Expense("Bus ticket", 3.0f, "Transport"));

        adapter = new ExpenseAdapter(this, data);
        expensesList.setAdapter(adapter);
        updateTotal();

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.addButton);
        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            addExpenseLauncher.launch(intent);
        });
    }

    private void updateTotal() {
        float total = 0f;
        for (Expense e : data) total += e.amount;
        totalText.setText(String.format(Locale.getDefault(), "Total: €%.2f", total));
    }
}