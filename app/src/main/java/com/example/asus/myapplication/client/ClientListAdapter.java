package com.example.asus.myapplication.client;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.asus.myapplication.R;
import java.util.ArrayList;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListViewHolder> {

    private Context context;
    private ArrayList<String> IDComp, CompName, Address;
     CardView card_view;

    public ClientListAdapter(Context _context, ArrayList<String> IDComp, ArrayList<String> CompName, ArrayList<String> Address) {
        this.context = _context;
        this.IDComp = IDComp;
        this.CompName = CompName;
        this.Address = Address;

    }

    @Override
    public ClientListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.client_list, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new ClientListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClientListViewHolder holder, int position) {

        //BIND DATA
        holder.card_view.setCardBackgroundColor(Color.rgb(255, 255, 255));
        holder.IDComp.setText("ID: " + IDComp.get(position));
        holder.CompName.setText("Nome: " + CompName.get(position));
        holder.Address.setText("Local: " + Address.get(position));


    }

    @Override
    public int getItemCount() {
        return CompName.size();
    }
}
