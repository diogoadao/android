package com.example.asus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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

public class Logs_List extends AppCompatActivity {
    private ImageButton mbutton;
    private Context _context;
    private ArrayList<String> Username = new ArrayList<String>();
    private ArrayList<String> LogID = new ArrayList<String>();
    private ArrayList<String> Date = new ArrayList<String>();
    private RecyclerView rv;
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logs_recycler);
        _context = this;
        mbutton = findViewById(R.id.imageButton3);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logs_List.this, Main_Menu.class);
                Logs_List.this.startActivity(intent);
            }
        });
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/android/api/api.php?action=Logs")
                .build();
        Log.i("info", "request built: Confirmed");
        Call myCall = okHttpClient.newCall(request);
        myCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i("info", "request got response: response");
                final String myResponse = response.body().string();
                try {
                    JSONArray jObj = new JSONArray(myResponse);
                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject obj = jObj.getJSONObject(i);
                        String id_log = obj.getString("id_log");
                        String fname = obj.getString("fname");
                        String lname = obj.getString("lname");
                        String date = obj.getString("date");
                        Date.add(date);
                        Username.add(fname+" "+lname);
                        LogID.add(id_log);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(_context, 1));
        rv.setHasFixedSize(true);
        Logs_List_Adapter adapter = new Logs_List_Adapter(_context,LogID,Username,Date);
        try {
            TimeUnit.MILLISECONDS.sleep(80);
            Log.i("info", "adapter created: Confirmed");
            rv.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
