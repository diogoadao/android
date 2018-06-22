package com.example.asus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Crm_List_Adapter extends RecyclerView.Adapter<Crm_List_ViewHolder> {
//CRM'S
    Context _context;
    ArrayList<String> IDCRM, exec, startdate , enddate , company ,State;
    CardView card_view;

    public Crm_List_Adapter(Context _context, ArrayList<String> idcrm, ArrayList<String> exec, ArrayList<String> startdate , ArrayList<String> enddate, ArrayList<String> company, ArrayList<String> state) {
        this._context = _context;
        this.IDCRM = idcrm;
        this.exec = exec;
        this.startdate = startdate;
        this.enddate = enddate;
        this.State = state;
        this.company = company;

    }

    @Override
    public Crm_List_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.crm, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new Crm_List_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Crm_List_ViewHolder holder, int position) {

        //BIND DATA
        if (State.get(position).contains("0")) {
            holder.card_view.setCardBackgroundColor(Color.rgb(255, 209, 26));//Em Curso
        } else if (State.get(position).contains("1")){
            holder.card_view.setCardBackgroundColor(Color.rgb(255, 173, 51));//Concluídos mas não fechados
        }else{
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
