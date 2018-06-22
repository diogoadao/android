package com.example.asus.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Logs_List_ViewHolder extends RecyclerView.ViewHolder {

    TextView Log_ID, Date, Username;
    CardView card_view;

    public Logs_List_ViewHolder(View itemView) {
        super(itemView);
        Log_ID = itemView.findViewById(R.id.Logid);
        Date = itemView.findViewById(R.id.Date);
        Username = itemView.findViewById(R.id.username);
        card_view = itemView.findViewById(R.id.card_view);
    }
}
