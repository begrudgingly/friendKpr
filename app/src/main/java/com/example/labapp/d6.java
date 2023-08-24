package com.example.labapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class d6 extends AppCompatActivity {

    PieChart pieChart;
    PieData pieData;
    List<PieEntry> pieEntryList = new ArrayList<>();
    private DBHandler dbHandler;
    TextView maleCount, femaleCount;
    String maleCountT, femaleCountT;
    int [] color = { Color.rgb(172, 90, 154),
            Color.rgb(95, 105, 193) };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d6);

        dbHandler = new DBHandler(d6.this);
        long countFriendM = dbHandler.getFriendCount("male");
        long countFriendF = dbHandler.getFriendCount("female");

        maleCount = findViewById(R.id.idMaleCount);
        maleCountT = Long.toString(countFriendM);
        maleCount.setText(maleCountT);

        femaleCount = findViewById(R.id.idFemaleCount);
        femaleCountT = Long.toString(countFriendF);
        femaleCount.setText(femaleCountT);

        pieChart = findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieEntryList.add(new PieEntry(countFriendM,"male"));
        pieEntryList.add(new PieEntry(countFriendF,"female"));
        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"Friends");
        pieDataSet.setColors(color);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextSize(12f);
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}