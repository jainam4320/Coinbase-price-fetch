package com.example.coinbase;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    String[] symbol = {"BTC","ETH","XRP","LTC","BCH","EOS","BNB","XLM","ADA","USDT"};
    Context mContext;
    List<Crypto> mData;
    Dialog myDialog;

    public RecycleViewAdapter(Context mContext, List<Crypto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.crypto_item,viewGroup,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog Ini

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_crypto);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        vHolder.cryptoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialogName = (TextView) myDialog.findViewById(R.id.dialogName);
                final TextView dialogPrice = (TextView) myDialog.findViewById(R.id.dialogPrice);
                ImageView dialogImage = (ImageView) myDialog.findViewById(R.id.dialogImage);
             /*{"BTC":{"USD":5040.93},"ETH":{"USD":161.4},"XRP":{"USD":0.3174},"LTC":{"USD":78.08},"BCH":{"USD":306.81},"EOS":{"USD":5.34},"BNB":{"USD":19.24},"XLM":{"USD":0.114},"ADA":{"USD":0.08094},"USDT":{"USD":1}}*/
                String url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH,XRP,LTC,BCH,EOS,BNB,XLM,ADA,USDT&tsyms=USD&api_key=b6eb0367f9c4a6291a8d83d6b1fdea349d0d6cbfce59c6cb5bd4c4fa8124cbda";

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()  {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject mainObject = response.getJSONObject(symbol[vHolder.getAdapterPosition()]);
                            String price = mainObject.getString("USD");
                            dialogPrice.setText(price + " $");

                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(mContext);
                queue.add(jor);

                dialogName.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialogImage.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
                myDialog.show();
            }
        });
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.cryptoName.setText(String.valueOf(mData.get(i).getName()));
        myViewHolder.cryptoImage.setImageResource(mData.get(i).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout cryptoItem;
        private TextView cryptoName;
        private ImageView cryptoImage;

        public MyViewHolder(View itemView) {
            super(itemView);

        cryptoName = (TextView) itemView.findViewById(R.id.cryptoName);
        cryptoImage = (ImageView) itemView.findViewById(R.id.cryptoImage);
        cryptoItem = (LinearLayout) itemView.findViewById(R.id.crypto_item);

        }
    }

}
