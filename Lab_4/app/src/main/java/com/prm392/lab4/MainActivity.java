package com.prm392.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static String orderedFood = null;
    private static String orderedDrink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOrderFood = findViewById(R.id.btnOrderFood);
        Button btnOrderDrink = findViewById(R.id.btnOrderDrink);
        TextView tvOrderedFood = findViewById(R.id.tvOrderedFood);
        TextView tvOrderedDrink = findViewById(R.id.tvOrderedDrink);

        String newOrderedFood = getIntent().getStringExtra("orderedFood");
        String newOrderedDrink = getIntent().getStringExtra("orderedDrink");
        if (newOrderedFood != null) {
            orderedFood = newOrderedFood;
        }
        if (orderedFood != null) {
            tvOrderedFood.setText("Món đã chọn: " + orderedFood);
        } else {
            tvOrderedFood.setText("Món đã chọn: Chưa có");
        }

        if (newOrderedDrink != null) {
            orderedDrink = newOrderedDrink;
        }
        if (orderedDrink != null) {
            tvOrderedDrink.setText("Đồ uống đã chọn: " + orderedDrink);
        } else {
            tvOrderedDrink.setText("Đồ uống đã chọn: Chưa có");
        }

        btnOrderFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(intent);
        });

        btnOrderDrink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
            startActivity(intent);
        });
    }
}