package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText amountInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addExpenseLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        nameInput = findViewById(R.id.nameInput);
        amountInput = findViewById(R.id.amountInput);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String amountStr = amountInput.getText().toString().trim();

            boolean ok = true;

            if (name.isEmpty()) {
                nameInput.setError(getString(R.string.error_name_required));
                ok = false;
            } else {
                nameInput.setError(null);
            }

            float amount = 0f;

            if (amountStr.isEmpty()) {
                amountInput.setError(getString(R.string.error_amount_required));
                ok = false;
            } else {
                try {
                    amount = Float.parseFloat(amountStr);
                    if (amount <= 0f) {
                        amountInput.setError(getString(R.string.error_amount_positive));
                        ok = false;
                    } else {
                        amountInput.setError(null);
                    }
                } catch (NumberFormatException e) {
                    amountInput.setError(getString(R.string.error_amount_invalid));
                    ok = false;
                }
            }

            if (!ok) return;

            Intent data = new Intent();
            data.putExtra("extra_name", name);
            data.putExtra("extra_amount", amount);
            setResult(RESULT_OK, data);

            finish();
        });
    }
}
