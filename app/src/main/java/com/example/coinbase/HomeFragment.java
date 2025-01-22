package com.example.coinbase;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Crypto> listCrypto;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.homeRecyclerView);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), listCrypto);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listCrypto = new ArrayList<>();
        listCrypto.add(new Crypto("Bitcoin",R.drawable.bitcoin));
        listCrypto.add(new Crypto("Ethereum",R.drawable.ethereum));
        listCrypto.add(new Crypto("Ripple",R.drawable.ripple));
        listCrypto.add(new Crypto("Litecoin",R.drawable.litecoin));
        listCrypto.add(new Crypto("Bitcoin Cash",R.drawable.bitcoin_cash));
        listCrypto.add(new Crypto("EOS",R.drawable.eos));
        listCrypto.add(new Crypto("Binance",R.drawable.binance));
        listCrypto.add(new Crypto("Stellar",R.drawable.stellar));
        listCrypto.add(new Crypto("Cardano",R.drawable.cardano));
        listCrypto.add(new Crypto("Tether",R.drawable.tether));

    }

}


