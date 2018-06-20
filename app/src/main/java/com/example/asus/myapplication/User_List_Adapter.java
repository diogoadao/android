package com.example.asus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class User_List_Adapter extends RecyclerView.Adapter<User_List_ViewHolder> {
    Context _context;
    ArrayList<String> userid, email, completed, done, inprogress, username, state;
    CardView card_view;

    public User_List_Adapter(Context _context, ArrayList<String> userid, ArrayList<String> email, ArrayList<String> completed, ArrayList<String> done, ArrayList<String> inprogress, ArrayList<String> username, ArrayList<String> state) {
        this._context = _context;
        this.userid = userid;
        this.email = email;
        this.completed = completed;
        this.done = done;
        this.inprogress = inprogress;
        this.username = username;
        this.state = state;

    }

    @Override
    public User_List_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(_context).inflate(R.layout.userlist, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        v.setMinimumHeight(height);
        return new User_List_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(User_List_ViewHolder holder, int position) {

        //BIND DATA

        if (state.get(position).contains("Inativo")) {
            holder.card_view.setCardBackgroundColor(Color.rgb(255, 153, 0));
        } else {
            holder.card_view.setCardBackgroundColor(Color.rgb(0, 255, 0));
        }
        holder.username.setText(username.get(position));
        holder.userid.setText(userid.get(position));
        holder.email.setText("teste");


    }

    @Override
    public int getItemCount() {
        return username.size();
    }
}
