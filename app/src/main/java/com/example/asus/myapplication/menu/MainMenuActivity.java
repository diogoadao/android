package com.example.asus.myapplication.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.asus.myapplication.R;
import com.example.asus.myapplication.utils.StrictModeController;
import com.github.mikephil.charting.charts.PieChart;


public class MainMenuActivity extends AppCompatActivity {
    private final MainMenuController Menu = new MainMenuController();
    private final StrictModeController control = new StrictModeController();
    public PieChart pieChart;
    private ImageButton mbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        control.turnStrict();
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
                        Menu.goToActivity(item.getItemId(), MainMenuActivity.this);

                        return true;
                    }
                });
                popup.show();
            }
        });
    }


}