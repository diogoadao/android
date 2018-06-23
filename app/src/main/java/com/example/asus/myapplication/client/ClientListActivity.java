package com.example.asus.myapplication.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.menu.MainMenuActivity;
import com.example.asus.myapplication.utils.StrictModeController;

public class ClientListActivity extends AppCompatActivity {
    private final StrictModeController control = new StrictModeController();
    private ImageButton mbutton;
    private RecyclerView rv;
    private ClientListController client = new ClientListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_recycler);
        control.turnStrict();
        mbutton = findViewById(R.id.imageButton3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientListActivity.this, MainMenuActivity.class);
                ClientListActivity.this.startActivity(intent);
            }
        });
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ClientListAdapter adapter = new ClientListAdapter(ClientListActivity.this, client.GetIdClient(), client.GetClientName(), client.GetAddress());
                Log.i("info", "adapter created: Confirmed");
                rv.setAdapter(adapter);
            }
        };
        client.GetList();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setHasFixedSize(true);
        handler.postDelayed(runnable, 500);

    }
}
