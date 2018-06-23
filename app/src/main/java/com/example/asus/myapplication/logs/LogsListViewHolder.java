package com.example.asus.myapplication.logs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.myapplication.R;

public class LogsListViewHolder extends RecyclerView.ViewHolder {

    private TextView Log_ID, Date, Username;
    private CardView card_view;

    public LogsListViewHolder(View itemView) {
        super(itemView);
        Log_ID = itemView.findViewById(R.id.Logid);
        Date = itemView.findViewById(R.id.Date);
        Username = itemView.findViewById(R.id.username);
        card_view = itemView.findViewById(R.id.card_view);
    }
}
