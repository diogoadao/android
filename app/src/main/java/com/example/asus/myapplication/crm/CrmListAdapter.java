package com.example.asus.myapplication.crm;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.myapplication.R;

import java.util.ArrayList;

public class CrmListAdapter extends RecyclerView.Adapter<CrmListViewHolder> {
    //CRM'S
    private Context _context;
    private ArrayList<String> IDCRM, exec, startdate, enddate, company, State;

    public CrmListAdapter(Context _context, ArrayList<String> idcrm, ArrayList<String> exec, ArrayList<String> startdate, ArrayList<String> enddate, ArrayList<String> company, ArrayList<String> state) {
        this._context = _context;
        this.IDCRM = idcrm;
        this.exec = exec;
        this.startdate = startdate;
        this.enddate = enddate;
        this.State = state;
        this.company = company;

    }

    @Override
    public CrmListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.crm, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new CrmListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CrmListViewHolder holder, int position) {

        //BIND DATA
        if (State.get(position).contains("0")) {
            holder.card_view.setCardBackgroundColor(Color.rgb(255, 209, 26));//Em Curso
        } else if (State.get(position).contains("1")) {
            holder.card_view.setCardBackgroundColor(Color.rgb(255, 173, 51));//Concluídos mas não fechados
        } else {
            holder.card_view.setCardBackgroundColor(Color.rgb(0, 179, 60));//Concluídos
        }
        holder.CRM_ID.setText("ID: " + IDCRM.get(position));
        holder.Exec.setText("Responsavel: " + exec.get(position));
        holder.StartDate.setText("Inicio: " + startdate.get(position));
        holder.EndDate.setText("Limite: " + enddate.get(position));
        holder.CompName.setText("Empresa: " + company.get(position));


    }

    @Override
    public int getItemCount() {
        return company.size();
    }
}
