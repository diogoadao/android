package com.example.asus.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Crm_List_ViewHolder extends RecyclerView.ViewHolder {

    TextView CRM_ID, StartDate,EndDate, Exec,CompName;
    CardView card_view;

    public Crm_List_ViewHolder(View itemView) {
        super(itemView);
        CRM_ID = itemView.findViewById(R.id.IDCRM);
        StartDate = itemView.findViewById(R.id.startdate);
        EndDate = itemView.findViewById(R.id.enddate);
        Exec = itemView.findViewById(R.id.exec);
        CompName = itemView.findViewById(R.id.Company);
        card_view = itemView.findViewById(R.id.card_view);
    }
}
