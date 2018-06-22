package com.example.asus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Logs_List_Adapter extends RecyclerView.Adapter<Logs_List_ViewHolder> {

    Context _context;
    ArrayList<String> logid, username, date;
    CardView card_view;

    public Logs_List_Adapter(Context _context, ArrayList<String> logid, ArrayList<String> username, ArrayList<String> date) {
        this._context = _context;
        this.logid = logid;
        this.username = username;
        this.date = date;

    }

    @Override
    public Logs_List_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.logs, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new Logs_List_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Logs_List_ViewHolder holder, int position) {

        //BIND DATA
        holder.card_view.setCardBackgroundColor(Color.rgb(255, 255, 255));
        holder.Username.setText("Nome: " + username.get(position));
        holder.Log_ID.setText("Login ID: " + logid.get(position));
        holder.Date.setText("Hora de Acesso: " + date.get(position));


    }

    @Override
    public int getItemCount() {
        return username.size();
    }
}
