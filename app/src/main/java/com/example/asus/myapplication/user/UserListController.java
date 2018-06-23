package com.example.asus.myapplication.user;

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

public class UserListController {

    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<String> UserID = new ArrayList<String>();
    private ArrayList<String> Email = new ArrayList<String>();
    private ArrayList<String> Work = new ArrayList<String>();
    private ArrayList<String> Completed = new ArrayList<String>();
    private ArrayList<String> Done = new ArrayList<String>();
    private ArrayList<String> State = new ArrayList<String>();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public boolean GetList() {
        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=Users")
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }


    public ArrayList<String> getUser() {
        return this.username;
    }

    public ArrayList<String> getUserID() {
        return this.UserID;
    }

    public ArrayList<String> getUserEmail() {
        return this.Email;
    }

    public ArrayList<String> getUserWork() {
        return this.Work;
    }

    public ArrayList<String> getUserCompleted() {
        return this.Completed;
    }

    public ArrayList<String> getUserState() {
        return this.State;
    }

    public ArrayList<String> getUserDone() {
        return this.Done;
    }

    public void gotoMenu(Context context) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }

}
