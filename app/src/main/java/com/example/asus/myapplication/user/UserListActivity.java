package com.example.asus.myapplication.user;

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
import com.example.asus.myapplication.utils.StrictModeController;

public class UserListActivity extends AppCompatActivity {
    private final UserListController data = new UserListController();
    private final StrictModeController control = new StrictModeController();
    private RecyclerView rv;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_recycler);
        ImageButton mbutton = findViewById(R.id.imageButton3);
        control.turnStrict();
        Log.i("info", "onCreate: imagebutton");
        final ProgressDialog prog = new ProgressDialog(this);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UserListAdapter adapter = new UserListAdapter(UserListActivity.this, data.getUserID(), data.getUserEmail(), data.getUserCompleted(), data.getUserDone(), data.getUserWork(), data.getUser(), data.getUserState());
                Log.i("info", "adapter created: Confirmed");
                rv.setAdapter(adapter);
                prog.dismiss();

            }
        };
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.gotoMenu(UserListActivity.this);
            }
        });
        prog.setTitle(getString(R.string.pleaseWait));
        prog.setMessage(getString(R.string.loading));
        prog.setCancelable(false);
        prog.setIndeterminate(true);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.show();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setHasFixedSize(true);
        data.GetList(handler, runnable);

        //handler.postDelayed(runnable, 500);

    }

}
