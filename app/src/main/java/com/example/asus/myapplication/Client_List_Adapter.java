package com.example.asus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Client_List_Adapter extends RecyclerView.Adapter<Client_List_ViewHolder> {

    Context _context;
    ArrayList<String> IDComp, CompName, Address;
    CardView card_view;

    public Client_List_Adapter(Context _context, ArrayList<String> IDComp, ArrayList<String> CompName, ArrayList<String> Address) {
        this._context = _context;
        this.IDComp = IDComp;
        this.CompName = CompName;
        this.Address = Address;

    }

    @Override
    public Client_List_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.client_list, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new Client_List_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Client_List_ViewHolder holder, int position) {

        //BIND DATA
        holder.card_view.setCardBackgroundColor(Color.rgb(0, 153, 51));
        holder.IDComp.setText("ID: " + IDComp.get(position));
        holder.CompName.setText("Nome: " + CompName.get(position));
        holder.Address.setText("Local: " + Address.get(position));


    }

    @Override
    public int getItemCount() {
        return CompName.size();
    }
}
