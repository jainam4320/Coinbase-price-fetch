package com.example.coinbase;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.coinbase.Constants.URL_SUBMIT;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {

    View v;
    public AccountFragment() {
        // Required empty public constructor
    }

    EditText editTextAmount, editTextShortDesc, editTextAverage, editTextImage;
    TextView textViewUsername;
    Button btnSubmit;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_account, container, false);
        editTextAmount = (EditText) v.findViewById(R.id.editTextName);
        textViewUsername = (TextView) v.findViewById(R.id.textViewUsername);
        textViewUsername.setText(SharedPrefManager.getInstance(getContext()).getUsername());
        progressBar =  (ProgressBar) v.findViewById(R.id.accountProgressBar);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        return v;
    }

    private void submitCrypto() {
        final String amount = editTextAmount.getText().toString().trim();
        final String username = SharedPrefManager.getInstance(getContext()).getUsername();

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SUBMIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    sendToMain();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("amount",amount);
                return params;
            }
        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(getContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit)
        {
            submitCrypto();
        }
    }


}
