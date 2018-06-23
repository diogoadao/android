package com.example.asus.myapplication.user;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.myapplication.R;

public class UserListViewHolder extends RecyclerView.ViewHolder {
    TextView userid, email, completed, done, work, username;
    CardView card_view;

    public UserListViewHolder(View itemView) {
        super(itemView);
        userid = itemView.findViewById(R.id.iduser);
        email = itemView.findViewById(R.id.useremail);
        completed = itemView.findViewById(R.id.completed);
        done = itemView.findViewById(R.id.done);
        work = itemView.findViewById(R.id.work);
        username = itemView.findViewById(R.id.username);
        card_view = itemView.findViewById(R.id.card_view);
    }
}

