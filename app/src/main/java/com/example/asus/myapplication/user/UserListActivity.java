package com.example.asus.myapplication.user;

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
    private ImageButton mbutton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_recycler);
        mbutton = findViewById(R.id.imageButton3);
        control.turnStrict();
        Log.i("info", "onCreate: imagebutton");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UserListAdapter adapter = new UserListAdapter(UserListActivity.this, data.getUserID(), data.getUserEmail(), data.getUserCompleted(), data.getUserDone(), data.getUserWork(), data.getUser(), data.getUserState());
                Log.i("info", "adapter created: Confirmed");
                rv.setAdapter(adapter);
            }
        };
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.GotoMenu(UserListActivity.this);
            }
        });
        rv = findViewById(R.id.rv);

        data.GetList();
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        rv.setHasFixedSize(true);
        handler.postDelayed(runnable, 500);

    }

}
