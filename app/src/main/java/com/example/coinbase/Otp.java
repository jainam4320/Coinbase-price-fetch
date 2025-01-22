package com.example.coinbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Otp extends AppCompatActivity implements View.OnClickListener {

    EditText editTextOtp;
    Button btnOtp;
    String otp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        setTitle("OTP");

        editTextOtp = (EditText) findViewById(R.id.editTextOtp);
        btnOtp = (Button) findViewById(R.id.btnOtp);
        otp = getIntent().getExtras().getString("otp");
        btnOtp.setOnClickListener(this);
    }

    private void checkOtp() {
        final String userotp = editTextOtp.getText().toString().trim();

        if(userotp == otp)
        {
            Toast.makeText(this, "Correct OTP", Toast.LENGTH_SHORT).show();
            sendToHome();
        }
        else
        {
            Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
            sendToRegister();
        }
    }

    private void sendToHome() {
        Intent loginIntent = new Intent(Otp.this, MainActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void sendToRegister() {
        Intent registerIntent = new Intent(Otp.this, RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v == btnOtp)
            checkOtp();
    }
}
