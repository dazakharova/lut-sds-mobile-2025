package com.example.expensetracker;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        TextView totalText = (TextView) findViewById(R.id.totalText);
        ListView expensesList = (ListView) findViewById(R.id.expensesList);

        List<Expense> data = new ArrayList<>();
        data.add(new Expense("Coffee", 2.5f));
        data.add(new Expense("Groceries", 15.75f));
        data.add(new Expense("Bus ticket", 3.0f));

        ExpenseAdapter adapter = new ExpenseAdapter(this, data);
        expensesList.setAdapter(adapter);
    }
}