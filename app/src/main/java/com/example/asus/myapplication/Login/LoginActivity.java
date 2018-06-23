package com.example.asus.myapplication.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asus.myapplication.Menu.MainMenuActivity;
import com.example.asus.myapplication.R;
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

    private Button loginbtn;
    private EditText mypass , myemail;
    private boolean login_bool;
    private ProgressDialog prog;
    ConstraintLayout rellay1;
    ImageView logo;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };

    public static String data;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
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
        loginbtn = (Button) findViewById(R.id.btn_login);
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
        rellay1 = (ConstraintLayout) findViewById(R.id.rellay1);
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
                    login_bool = login.LoginCheck(jObj);

                    Log.d("Info", "Boolean state"+login_bool);
                    if (login_bool == true) {
                        Log.d("Info", "Login approved v2 asdas");
                        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else {

                        Log.d("Info", "Login Denied v2");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}