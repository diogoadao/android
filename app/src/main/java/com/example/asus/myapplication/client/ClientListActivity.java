package com.example.asus.myapplication.client;

import android.app.ProgressDialog;
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
import com.example.asus.myapplication.login.LoginActivity;
import com.example.asus.myapplication.menu.MainMenuActivity;
import com.example.asus.myapplication.utils.StrictModeController;

public class ClientListActivity extends AppCompatActivity {
    private final StrictModeController control = new StrictModeController();
    private RecyclerView rv;
    private ClientListController client = new ClientListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_recycler);
        control.turnStrict();

        ImageButton mbutton = findViewById(R.id.imageButton3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientListActivity.this, MainMenuActivity.class);
                ClientListActivity.this.startActivity(intent);
            }
        });
        final ProgressDialog prog = new ProgressDialog(this);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ClientListAdapter adapter = new ClientListAdapter(ClientListActivity.this, client.getIdClient(), client.getClientName(), client.getAddress());
                Log.i("info", "adapter created: Confirmed");
                rv.setAdapter(adapter);
                prog.dismiss();
            }
        };

        prog.setTitle(getString(R.string.pleaseWait));
        prog.setMessage(getString(R.string.loading));
        prog.setCancelable(false);
        prog.setIndeterminate(true);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.show();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setHasFixedSize(true);
        client.getList(handler,runnable);
        //handler.postDelayed(runnable, 500);

    }
}
