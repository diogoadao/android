package com.example.asus.myapplication.Client;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.myapplication.R;

public class ClientListViewHolder extends RecyclerView.ViewHolder {

    TextView IDComp, CompName, Address;
    CardView card_view;

    public ClientListViewHolder(View itemView) {
        super(itemView);
        Address = itemView.findViewById(R.id.ecompanyAddress);
        CompName = itemView.findViewById(R.id.company);
        IDComp = itemView.findViewById(R.id.IDCompany);
        card_view = itemView.findViewById(R.id.card_view);
    }
}
