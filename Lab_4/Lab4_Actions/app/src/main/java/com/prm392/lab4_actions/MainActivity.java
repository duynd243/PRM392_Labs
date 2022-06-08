package com.prm392.lab4_actions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageBrowser = findViewById(R.id.imageBrowser);
        ImageView imageMessage = findViewById(R.id.imageMessage);
        ImageView imagePhone = findViewById(R.id.imagePhone);

        imageBrowser.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            startActivity(browserIntent);
        });

        imageMessage.setOnClickListener(v -> {
            String phoneNumber = "0989898989";
            Intent messageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));

            messageIntent.putExtra("sms_body", "Hello");
            startActivity(messageIntent);
        });

        imagePhone.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                String phoneNumber = "0989898989";
                Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(phoneIntent);
            }
        });
    }
}