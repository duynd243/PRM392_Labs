package com.prm392.passingdata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            String [] cityArr = {"Hanoi", "HCM", "Da Nang", "Hai Phong"};
            Student student = new Student("Nguyen Dinh Duy", "SE150396");
            Bundle bundle = new Bundle();
            bundle.putString("string", "Truyen data voi Bundle");
            bundle.putInt("number", 511);
            bundle.putStringArray("array", cityArr);
            bundle.putSerializable("student", student);

            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        });
    }
}