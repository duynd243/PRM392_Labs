package com.duynd.lab2_ex2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private void updateDisplay(Operation operation, EditText editTextFirstNumber, EditText editTextSecondNumber, TextView textViewResult) {
        try {
            boolean isValid = true;
            String firstNumberStr = editTextFirstNumber.getText().toString();
            String secondNumberStr = editTextSecondNumber.getText().toString();
            if (firstNumberStr.trim().isEmpty()) {
                editTextFirstNumber.setError("Please enter a number");
                isValid = false;
            }
            if (secondNumberStr.trim().isEmpty()) {
                editTextSecondNumber.setError("Please enter a number");
                isValid = false;
            }
            if (!isValid) {
                return;
            }
            int first = Integer.parseInt(firstNumberStr);
            int second = Integer.parseInt(secondNumberStr);
            switch (operation) {
                case ADD:
                    textViewResult.setText(String.format("Result: %d + %d = %d", first, second, first + second));
                    break;
                case SUBTRACT:
                    textViewResult.setText(String.format("Result: %d - %d = %d", first, second, first - second));
                    break;
                case MULTIPLY:
                    textViewResult.setText(String.format("Result: %d * %d = %d", first, second, first * second));
                    break;
                case DIVIDE:
                    if (second == 0) {
                        Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    textViewResult.setText(String.format("Result: %d / %d = %f", first, second, (first / (double) second)));
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditTexts
        EditText editTextFirstNumber = findViewById(R.id.editTextFirstNumber);
        EditText editTextSecondNumber = findViewById(R.id.editTextSecondNumber);

        // Buttons
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        // TextViews
        TextView textViewResult = findViewById(R.id.textViewResult);


        btnAdd.setOnClickListener(v -> updateDisplay(Operation.ADD, editTextFirstNumber, editTextSecondNumber, textViewResult));
        btnSubtract.setOnClickListener(v -> updateDisplay(Operation.SUBTRACT, editTextFirstNumber, editTextSecondNumber, textViewResult));
        btnMultiply.setOnClickListener(v -> updateDisplay(Operation.MULTIPLY, editTextFirstNumber, editTextSecondNumber, textViewResult));
        btnDivide.setOnClickListener(v -> updateDisplay(Operation.DIVIDE, editTextFirstNumber, editTextSecondNumber, textViewResult));
    }
}