package com.example.asus.myapplication.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.utils.StrictModeController;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public static String data;
    private final StrictModeController control = new StrictModeController();
    Handler handler = new Handler();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    private Button loginbtn;
    private EditText mypass, myemail;
    private boolean login_bool;
    private Context context;
    private ProgressDialog prog;
    private ConstraintLayout rellay1;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };
    private ImageView logo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        control.turnStrict();
        context = this;
        loginbtn = findViewById(R.id.btn_login);
        myemail = findViewById(R.id.myemail);
        mypass = findViewById(R.id.mypass);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog = new ProgressDialog(LoginActivity.this);
                prog.setTitle(getString(R.string.pleaseWait));
                prog.setMessage(getString(R.string.webpage_being_loaded));
                prog.setCancelable(false);
                prog.setIndeterminate(true);
                prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prog.show();
                doSimpleGet(myemail.getText().toString(), mypass.getText().toString());

            }
        });
        rellay1 = findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 2000);


    }

    public void doSimpleGet(String Email, String Pass) {

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
                    LoginController login = new LoginController();
                    JSONObject jObj = new JSONObject(myResponse);
                    data = jObj.toString();
                    login_bool = login.LoginCheck(jObj, context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}