package com.example.asus.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class User_List_ViewHolder extends RecyclerView.ViewHolder {
    TextView userid, email, completed, done, inprogress, username;
    CardView card_view;

    public User_List_ViewHolder(View itemView) {
        super(itemView);
        userid = itemView.findViewById(R.id.iduser);
        email = itemView.findViewById(R.id.useremail);
        completed = itemView.findViewById(R.id.completed);
        done = itemView.findViewById(R.id.done);
        inprogress = itemView.findViewById(R.id.work);
        username = itemView.findViewById(R.id.username);
        card_view = itemView.findViewById(R.id.card_view);
    }
}

