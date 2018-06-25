package com.example.asus.myapplication.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.asus.myapplication.menu.MainMenuActivity;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginController {
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public boolean loginCheck(JSONObject Obj, Context context) throws JSONException {
        int i = Integer.parseInt(Obj.getString("acess_level"));
        if (i == 1) {
            Log.d("Info", "Login Approved");
            goToMenu(context);
            return true;
        } else {
            Log.d("Info", "Login Denied");
            return false;
        }
    }

    public void goToMenu(Context context) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }

    public void doSimpleGet(String Email, String Pass, final ProgressDialog prog,final Context context) {

        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=login&user=" + Email + "&pass=" + Pass)
                .build();
        Call myCall = okHttpClient.newCall(request);
        myCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                prog.dismiss();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String myResponse = response.body().string();
                try {
                    prog.dismiss();

                    JSONObject jObj = new JSONObject(myResponse);
                    loginCheck(jObj, context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
