package com.example.asus.myapplication.logs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.client.ClientListActivity;
import com.example.asus.myapplication.utils.StrictModeController;

public class LogsListActivity extends AppCompatActivity {
    private final LogsListController logs = new LogsListController();
    private final StrictModeController control = new StrictModeController();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logs_recycler);
        control.turnStrict();
        ImageButton mbutton = findViewById(R.id.imageButton3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logs.gotoMenu(LogsListActivity.this);
            }
        });
        final ProgressDialog prog = new ProgressDialog(this);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogsListAdapter adapter = new LogsListAdapter(LogsListActivity.this, logs.getLogID(), logs.getUser(), logs.getDate());
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
        logs.GetList(handler,runnable);
        //handler.postDelayed(runnable, 500);

    }
}
