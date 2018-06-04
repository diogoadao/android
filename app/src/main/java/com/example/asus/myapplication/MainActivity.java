package com.example.asus.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@EActivity(R.layout.login_activity)
public class MainActivity extends Activity {

    @ViewById(R.id.myemail)
    EditText myInput;

    @ViewById(R.id.textView)
    TextView textView;

    @Click
    void btn_login() {
        String name = myInput.getText().toString();
        someBackgroundWork(name);
    }
    public static String data;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }


    @Background
    void someBackgroundWork(String name) {

        doSimpleGet(name);
    }

    @UiThread
    void updateUi(String message) {
        textView.setText(message);
    }
    @Background
    public void doSimpleGet(String Input) {
        Request request = new Request.Builder()
                .url("http://192.168.2.252/android/api/api.php?action=login&user=dev@mc.com&pass=12345")
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
                    //JSONArray arr = new JSONArray(myResponse);
                    JSONObject jObj = new JSONObject(myResponse);
                    //JSONObject jObj = arr.getJSONObject(0);
                    //data = jObj.getString().toString();
                    data = jObj.toString();

                    Log.d("Error", "onResponse() returned: " + data);
                    updateUi(data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }
}