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

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.utils.StrictModeController;

public class LoginActivity extends AppCompatActivity {

    public static String data;
    private final StrictModeController control = new StrictModeController();
    private Handler handler = new Handler();
    private EditText mypass, myemail;
    private Context context;
    private ConstraintLayout rellay1;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };
    private LoginController login = new LoginController();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        control.turnStrict();
        context = this;
        Button loginbtn = findViewById(R.id.btn_login);
        myemail = findViewById(R.id.myemail);
        mypass = findViewById(R.id.mypass);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog prog = new ProgressDialog(LoginActivity.this);
                prog.setTitle(getString(R.string.pleaseWait));
                prog.setMessage(getString(R.string.webpage_being_loaded));
                prog.setCancelable(false);
                prog.setIndeterminate(true);
                prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prog.show();
                login.doSimpleGet(myemail.getText().toString(), mypass.getText().toString(), prog, context);


            }
        });
        rellay1 = findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 2000);
    }
}
