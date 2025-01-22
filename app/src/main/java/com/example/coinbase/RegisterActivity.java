package com.example.coinbase;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmailRegister, editTextPasswordRegister, editTextUsernameRegister, editTextMobileRegister;
    Button btnRegister;
    ProgressBar progressBar;
    String otp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setTitle("Register");

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }


        editTextEmailRegister = (EditText) findViewById(R.id.editTextEmailRegister);
        editTextPasswordRegister = (EditText) findViewById(R.id.editTextPasswordRegister);
        editTextUsernameRegister = (EditText) findViewById(R.id.editTextUsernameRegister);
        editTextMobileRegister = (EditText) findViewById(R.id.editTextMobileRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    private void registerUser(){
        final String email = editTextEmailRegister.getText().toString().trim();
        final String password = editTextPasswordRegister.getText().toString().trim();
        final String username = editTextUsernameRegister.getText().toString().trim();
        final String mobile = editTextMobileRegister.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);

                //{"error":true,"message":"Invalid Request"}
                try {
                    JSONObject jsonObject = new JSONObject(response);
                  Toast.makeText(RegisterActivity.this, "successful", Toast.LENGTH_SHORT).show();
                  otp = jsonObject.getString("message");
                  sendToOtp();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                params.put("mobile",mobile);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendToOtp() {
        Intent loginIntent = new Intent(RegisterActivity.this, Otp.class);
        loginIntent.putExtra("otp",otp);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister)
            registerUser();
    }
}
