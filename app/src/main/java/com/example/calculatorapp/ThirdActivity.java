package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private TextView textViewResult;
    private Button   buttonHome;
    private Button   buttonCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textViewResult   = findViewById(R.id.textViewResult);
        buttonHome       = findViewById(R.id.buttonHome);
        buttonCalculator = findViewById(R.id.buttonCalculator);

        String equation = getIntent().getStringExtra("EQUATION");
        String result   = getIntent().getStringExtra("RESULT");

        if (equation != null && result != null) {
            textViewResult.setText(equation + " = " + result);
        }

        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        buttonCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}

