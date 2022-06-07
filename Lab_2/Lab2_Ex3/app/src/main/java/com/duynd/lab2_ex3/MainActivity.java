package com.duynd.lab2_ex3;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setText("Hello, " + getIntent().getStringExtra("username"));
    }
}