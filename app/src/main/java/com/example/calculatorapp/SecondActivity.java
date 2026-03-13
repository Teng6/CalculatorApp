package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {


    private TextView textViewUserInput;
    private Button button0, button1, button2, button3, button4;
    private Button button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMultiply;
    private Button buttonDivide, buttonPercent, buttonEquals;


    private String currentInput   = "";
    private String operator       = "";
    private double firstNumber    = 0;
    private boolean newInput      = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();
        setNumberListeners();
        setOperatorListeners();
    }

    private void initViews() {
        textViewUserInput = findViewById(R.id.textViewUserInput);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd      = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide   = findViewById(R.id.buttonDivide);
        buttonPercent  = findViewById(R.id.buttonPercent);
        buttonEquals   = findViewById(R.id.buttonEquals);
    }

    private void setNumberListeners() {
        Button[] btns   = {button0,button1,button2,button3,button4,
                button5,button6,button7,button8,button9};
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        for (int i = 0; i < btns.length; i++) {
            final String d = digits[i];
            btns[i].setOnClickListener(v -> appendDigit(d));
        }
    }

    private void appendDigit(String digit) {
        if (newInput) { currentInput = ""; newInput = false; }
        if (currentInput.equals("0")) currentInput = "";
        currentInput += digit;
        textViewUserInput.setText(currentInput);
    }

    private void setOperatorListeners() {

        buttonAdd.setOnClickListener(v      -> selectOperator("+"));
        buttonSubtract.setOnClickListener(v -> selectOperator("-"));
        buttonMultiply.setOnClickListener(v -> selectOperator("*"));
        buttonDivide.setOnClickListener(v   -> selectOperator("/"));

        buttonPercent.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double val = Double.parseDouble(currentInput) / 100.0;
                currentInput = formatNum(val);
                textViewUserInput.setText(currentInput);
            }
        });

        buttonEquals.setOnClickListener(v -> {
            if (operator.isEmpty() || currentInput.isEmpty()) return;

            double secondNumber = Double.parseDouble(currentInput);
            double result;
            String equation = formatNum(firstNumber)
                    + " " + operator + " "
                    + formatNum(secondNumber);

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber == 0) {
                        textViewUserInput.setText("Cannot divide by 0");
                        return;
                    }
                    result = firstNumber / secondNumber; break;
                default: result = secondNumber; break;
            }

            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            intent.putExtra("EQUATION", equation);
            intent.putExtra("RESULT",   formatNum(result));
            startActivity(intent);

            currentInput = formatNum(result);
            operator = "";
            newInput = true;
            textViewUserInput.setText(currentInput);
        });
    }

    private void selectOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
        }
        operator = op;
        newInput = true;
        textViewUserInput.setText(formatNum(firstNumber) + " " + op);
    }

    private String formatNum(double value) {
        if (value == (long) value) return String.valueOf((long) value);
        return String.valueOf(value);
    }
}

