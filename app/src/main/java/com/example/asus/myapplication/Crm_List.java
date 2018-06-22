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

public class Crm_List extends AppCompatActivity {
    private ImageButton mbutton;
    private Context _context;
    private ArrayList<String> IDCRM = new ArrayList<String>();
    private ArrayList<String> exec = new ArrayList<String>();
    private ArrayList<String> Startdate = new ArrayList<String>();
    private ArrayList<String> Enddate = new ArrayList<String>();
    private ArrayList<String> Company = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
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
                Intent intent = new Intent(Crm_List.this, Main_Menu.class);
                startActivity(intent);
            }
        });
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/android/api/api.php?action=crm")
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
                Log.i("info", "request got response: response"+myResponse);
                try {
                    JSONArray jObj = new JSONArray(myResponse);
                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject obj = jObj.getJSONObject(i);
                        String id_crm = obj.getString("idcrm");
                        String fullname = obj.getString("fullname");
                        String name = obj.getString("name");
                        String startdate = obj.getString("startline");
                        String enddate = obj.getString("deadline");
                        String state = obj.getString("state");
                        IDCRM.add(id_crm);
                        exec.add(fullname);
                        Startdate.add(startdate);
                        Enddate.add(enddate);
                        Company.add(name);
                        State.add(state);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(_context, 1));
        rv.setHasFixedSize(true);
        Crm_List_Adapter adapter = new Crm_List_Adapter(_context, IDCRM, exec, Startdate , Enddate, Company, State);
        try {
            TimeUnit.MILLISECONDS.sleep(400);
            Log.i("info", "adapter created: Confirmed");
            rv.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
