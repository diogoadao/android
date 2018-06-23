package com.example.asus.myapplication.Menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.asus.myapplication.Client.ClientListActivity;
import com.example.asus.myapplication.Crm.CrmListActivity;
import com.example.asus.myapplication.Login.LoginActivity;
import com.example.asus.myapplication.Logs.LogsListActivity;
import com.example.asus.myapplication.R;
import com.example.asus.myapplication.User.UserListActivity;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainMenuActivity extends AppCompatActivity {
    public PieChart pieChart;
    private ImageButton mbutton;
    private final MainMenuController Menu = new MainMenuController();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
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
        mbutton = findViewById(R.id.imageButton3);
        pieChart = findViewById(R.id.chart);
        pieChart.setUsePercentValues(false);
        Menu.DoChart(pieChart);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDescriptionColor(Color.rgb(255, 255, 255));
        pieChart.setDescription("Numero De Tarefas Por estado");
        pieChart.getLegend().setEnabled(false);
        pieChart.animateXY(1400, 1400);
        mbutton = findViewById(R.id.menubtn);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainMenuActivity.this, mbutton);
                popup.getMenuInflater().inflate(R.menu.actions, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Menu.GoTO(item.getTitle().toString(), MainMenuActivity.this);

                        return true;
                    }
            });
                popup.show();
        }
    });
}




}