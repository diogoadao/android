package com.example.asus.myapplication.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.asus.myapplication.Menu.MainMenuActivity;
import com.example.asus.myapplication.R;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ImageButton mbutton;
    private final UserListController data = new UserListController();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_recycler);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeathOnNetwork()
                .penaltyFlashScreen()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build());
        mbutton = findViewById(R.id.imageButton3);
        Log.i("info", "onCreate: imagebutton");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UserListAdapter adapter = new UserListAdapter(UserListActivity.this, data.GetUserID(), data.GetUserEmail(), data.GetUserCompleted(), data.GetUserDone(), data.GetUserWork(), data.GetUser(), data.GetUserState());
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
