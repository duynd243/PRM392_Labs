package com.duynd.lab2_ex3;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private final String REQUIRE = "Require";

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonSignIn;
    TextView textViewCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        textViewCreate = findViewById(R.id.textViewCreate);

        textViewCreate.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(editTextUsername.getText().toString())) {
            editTextUsername.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            editTextPassword.setError(REQUIRE);
            return false;
        }

        return true;
    }

    public void signIn() {
        if (!checkInput()) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", editTextUsername.getText().toString());
        startActivity(intent);
        finish();
    }

    public void signUpForm() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                signIn();
                break;

            case R.id.textViewCreate:
                signUpForm();
                break;
        }
    }
}