package com.example.asus.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class User_List extends AppCompatActivity {
    RecyclerView rv;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    private Context context;
    private boolean boolres = false;
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<String> UserID = new ArrayList<String>();
    private ArrayList<String> Email = new ArrayList<String>();
    private ArrayList<String> Work = new ArrayList<String>();
    private ArrayList<String> Completed = new ArrayList<String>();
    private ArrayList<String> Done = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_recycler);
        context = this;
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/android/api/api.php?action=teste")
                .build();
        Call myCall = okHttpClient.newCall(request);
        myCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String myResponse = response.body().string();
                try {
                    JSONArray jObj = new JSONArray(myResponse);
                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject obj = jObj.getJSONObject(i);
                        String id = obj.getString("id");
                        String fname = obj.getString("fname");
                        String lname = obj.getString("lname");
                        String email = obj.getString("email");
                        String state = obj.getString("state");
                        UserID.add(id);
                        username.add(fname + " " + lname);
                        // Email.add(email);
                        State.add(state);
                        //Log.d("info", "onResponse:"+state);
                    }
                    boolres = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(context, 1));
        rv.setHasFixedSize(true);

        User_List_Adapter adapter = new User_List_Adapter(context, UserID, username, username, username, username, username, State);
        try {

            TimeUnit.MILLISECONDS.sleep(80);
            rv.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //request end
    }
}
