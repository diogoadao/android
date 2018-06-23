package com.example.asus.myapplication.logs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.asus.myapplication.menu.MainMenuActivity;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogsListController {

    private ArrayList<String> Username = new ArrayList<String>();
    private ArrayList<String> LogID = new ArrayList<String>();
    private ArrayList<String> Date = new ArrayList<String>();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public void GetList() {
        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=Logs")
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
                        Username.add(fname + " " + lname);
                        LogID.add(id_log);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ArrayList<String> getDate() {
        return Date;
    }

    public ArrayList<String> getUser() {
        return Username;
    }

    public ArrayList<String> getLogID() {
        return LogID;
    }

    public void gotoMenu(Context context) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }

}
