package com.example.coinbase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.coinbase.Constants.URL_CRYPTO;
import static com.example.coinbase.Constants.URL_TRANSACTION;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    TransactionAdapter adapter;

    List<Transaction> transactionList;

    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_transaction, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.transactionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionList = new ArrayList<>();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadCryptos();
    }

    private void loadCryptos() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRANSACTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray cryptos = new JSONArray(response);
                    Log.i("HEY", response);
                    for(int i=0;i<cryptos.length();i++)
                    {
                        JSONObject cryptoObject = cryptos.getJSONObject(i);
                        int id = cryptoObject.getInt("id");
                        String name = cryptoObject.getString("username");
                        String amount = cryptoObject.getString("amount");


                        Transaction transaction = new Transaction(id,name,amount);
                        transactionList.add(transaction);

                    }

                    adapter = new TransactionAdapter(getContext(), transactionList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext() , error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);


    }
}
