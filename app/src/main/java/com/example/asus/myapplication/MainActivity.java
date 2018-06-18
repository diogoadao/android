package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@EActivity(R.layout.login_activity)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.myemail)
    EditText _myEmail;

    @ViewById(R.id.mypass)
    EditText _myPass;

    @Click
    void btn_login() {
        String email = _myEmail.getText().toString();
        String pass = _myPass.getText().toString();
        doSimpleGet(email,pass);
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
    public void doSimpleGet(String Email , String Pass) {
        Request request = new Request.Builder()
                .url("http://192.168.2.252:81/android/api/api.php?action=login&user="+Email+"&pass="+Pass)
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
                    Login_Controller login = new Login_Controller();
                    JSONObject jObj = new JSONObject(myResponse);
                    data = jObj.toString();
                    boolean login_bool = login.LoginCheck(jObj);
                    if(login_bool == true){
                        Intent intent = new Intent(MainActivity.this, Main_Menu.class);
                        MainActivity.this.startActivity(intent);
                    }else{
                        Log.d("Info", "Login Denied v2");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }
}