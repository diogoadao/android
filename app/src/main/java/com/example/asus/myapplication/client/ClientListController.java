package com.example.asus.myapplication.client;

import android.util.Log;

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

public class ClientListController {
    private ArrayList<String> ClientName = new ArrayList<String>();
    private ArrayList<String> IdClient = new ArrayList<String>();
    private ArrayList<String> Address = new ArrayList<String>();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public void GetList() {

        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=Clients")
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
                        String name = obj.getString("name");
                        String addresss = obj.getString("addresss");
                        Address.add(addresss);
                        ClientName.add(name);
                        IdClient.add(id);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ArrayList<String> GetClientName() {
        return this.ClientName;
    }

    public ArrayList<String> GetIdClient() {
        return this.IdClient;
    }

    public ArrayList<String> GetAddress() {
        return this.Address;
    }
}
