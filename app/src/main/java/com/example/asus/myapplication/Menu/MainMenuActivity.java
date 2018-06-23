package com.example.asus.myapplication.Menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    public final ArrayList<Entry> yvalues = new ArrayList<Entry>();
    public PieChart pieChart;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    private ImageButton mbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        mbutton = findViewById(R.id.imageButton3);
        pieChart = findViewById(R.id.chart);
        pieChart.setUsePercentValues(false);
        DoChart();


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
                        switch ("" + item.getTitle()) {
                            case "Lista De Utilizadores":

                                Intent intent = new Intent(MainMenuActivity.this, UserListActivity.class);
                                startActivity(intent);
                                break;
                            case "Lista de Logins":

                                Intent intent2 = new Intent(MainMenuActivity.this, LogsListActivity.class);
                                startActivity(intent2);
                                break;
                            case "Lista de Clientes":

                                Intent intent3 = new Intent(MainMenuActivity.this, ClientListActivity.class);
                                startActivity(intent3);
                                break;
                            case "LogOut":

                                Intent intent4 = new Intent(MainMenuActivity.this, LoginActivity.class);
                                startActivity(intent4);
                                break;
                            case "Lista De Eventos":

                                Intent intent5 = new Intent(MainMenuActivity.this, CrmListActivity.class);
                                startActivity(intent5);
                                break;



                        }

                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    public void DoChart() {
        Request request = new Request.Builder()
                .url("http://thmc.ddns.net:81/android/api/api.php?action=PieChart")
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

                    JSONObject jObj = new JSONObject(myResponse);
                    Log.d("Info", "" + jObj.getString("open"));
                    Log.d("Info", "" + jObj.getString("executed"));
                    Log.d("Info", "" + jObj.getString("done"));
                    yvalues.add(new Entry(Integer.parseInt(jObj.getString("open")), 1));
                    yvalues.add(new Entry(Integer.parseInt(jObj.getString("executed")), 2));
                    yvalues.add(new Entry(Integer.parseInt(jObj.getString("done")), 3));
                    PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

                    ArrayList<String> xVals = new ArrayList<String>();

                    xVals.add("Em Curso");
                    xVals.add("Executado");
                    xVals.add("Fechado");
                    PieData data = new PieData(xVals, dataSet);
                    pieChart.setData(data);
                    pieChart.setTransparentCircleRadius(25f);
                    pieChart.setHoleRadius(25f);
                    pieChart.setHoleColor(0);
                    pieChart.setHighlightPerTapEnabled(false);

                    dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                    data.setValueTextSize(13f);
                    data.setValueTextColor(Color.DKGRAY);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}