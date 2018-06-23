package com.example.asus.myapplication.Crm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.asus.myapplication.Menu.MainMenuActivity;
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

public class CrmListController {
    private ArrayList<String> IDCRM = new ArrayList<String>();
    private ArrayList<String> exec = new ArrayList<String>();
    private ArrayList<String> Startdate = new ArrayList<String>();
    private ArrayList<String> Enddate = new ArrayList<String>();
    private ArrayList<String> Company = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();


    public void GetList(){

        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=crm")
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
    }
    public void GotoMenu(Context context){
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }
    public ArrayList<String> GetID() {
        return this.IDCRM;
    }

    public ArrayList<String> Getexec() {
        return this.exec;
    }

    public ArrayList<String> GetStartdate() {
        return this.Startdate;
    }

    public ArrayList<String> GetEnddate() {
        return this.Enddate;
    }

    public ArrayList<String> GetCompany() {
        return this.Company;
    }

    public ArrayList<String> GetState() {
        return this.State;
    }
}
