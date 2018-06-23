package com.example.asus.myapplication.Menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

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

public class MainMenuController {
    private final ArrayList<Entry> yvalues = new ArrayList<Entry>();
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public void GoTO(int Item, Context context) {
        switch (Item) {
            case R.id.userlist:

                Intent intent = new Intent(context, UserListActivity.class);
                context.startActivity(intent);
                break;
            case R.id.loglist:

                Intent intent2 = new Intent(context, LogsListActivity.class);
                context.startActivity(intent2);
                break;
            case R.id.clientlist:

                Intent intent3 = new Intent(context, ClientListActivity.class);
                context.startActivity(intent3);
                break;
            case R.id.logout:

                Intent intent4 = new Intent(context, LoginActivity.class);
                context.startActivity(intent4);
                break;
            case R.id.CRM:

                Intent intent5 = new Intent(context, CrmListActivity.class);
                context.startActivity(intent5);
                break;

        }
    }

    public void DoChart(final PieChart pieChart) {
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

