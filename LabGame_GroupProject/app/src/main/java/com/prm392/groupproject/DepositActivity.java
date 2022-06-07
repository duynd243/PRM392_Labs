package com.prm392.groupproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepositActivity extends AppCompatActivity {

    private static final List<String> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Button btnDeposit = findViewById(R.id.btnDeposit);
        EditText edtDepositAmount = findViewById(R.id.edtDepositAmount);
        TextView tvTeamMembers = findViewById(R.id.tvTeamMembers);

        members.clear();
        members.add("• Nguyễn Dương - SE150483");
        members.add("• Nguyễn Thiện Quang - SE150500");
        members.add("• Lê Anh Khoa - SE150498");
        members.add("• Tôn Trọng Nghĩa - SE150525");
        members.add("• Nguyễn Thế Hiển - SE150224");
        members.add("• Nguyễn Đình Duy - SE150396");

        String membersString = members.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));

        tvTeamMembers.setOnClickListener(v -> new AlertDialog.Builder(DepositActivity.this)
                .setTitle("Team Members")
                .setMessage(membersString)
                .setPositiveButton(android.R.string.ok, null)
                .show());

        btnDeposit.setOnClickListener(v -> {
            String amountStr = edtDepositAmount.getText().toString();
            if (amountStr.trim().isEmpty()) {
                edtDepositAmount.setError("Please enter deposit amount");
                edtDepositAmount.requestFocus();
                return;
            }
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= 0) {
                    edtDepositAmount.setError("Please enter a valid amount");
                    edtDepositAmount.requestFocus();
                    return;
                }
                Toast.makeText(this, "Successfully deposited $" + amountStr + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("depositAmount", amount);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(DepositActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}