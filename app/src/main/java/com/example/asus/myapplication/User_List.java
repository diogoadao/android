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
    private ImageButton mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_recycler);
        mbutton = findViewById(R.id.imageButton3);
        Log.i("info", "onCreate: imagebutton");
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_List.this, Main_Menu.class);
                User_List.this.startActivity(intent);
            }
        });
        context = this;
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/android/api/api.php?action=teste")
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
                        String id = obj.getString("id");
                        String fname = obj.getString("fname");
                        String lname = obj.getString("lname");
                        String email = obj.getString("email");
                        String state = obj.getString("state");
                        String work = obj.getString("work");
                        String done = obj.getString("done");
                        String completed = obj.getString("completed");
                        UserID.add(id);
                        username.add(fname + " " + lname);
                        Email.add(email);
                        State.add(state);
                        Work.add(work);
                        Done.add(done);
                        Completed.add(completed);
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

        User_List_Adapter adapter = new User_List_Adapter(context, UserID, Email, Completed, Done, Work, username, State);
        try {

            TimeUnit.MILLISECONDS.sleep(80);
            Log.i("info", "adapter created: Confirmed");
            rv.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //request end
    }
}
