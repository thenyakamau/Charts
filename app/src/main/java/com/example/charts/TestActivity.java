package com.example.charts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.charts.fragment.ChartFragmentPresenterImplementer;
import com.example.charts.model.ChartsModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        barChart = findViewById(R.id.bar_chart2);


        BarChart();

    }

    private void BarChart(){

        Toast.makeText(this, "This Bar Chart", Toast.LENGTH_SHORT).show();
        List<BarEntry> barEntry = new ArrayList<>();


        ArrayList<ChartsModel>response = new ArrayList<>();

        response.add(new ChartsModel(1,2010,20));
        response.add(new ChartsModel(2,2011,30));

        for(ChartsModel chartsModel: response){

            barEntry.add(new BarEntry(chartsModel.getYear(), chartsModel.getGrowthRate()));

        }

        BarDataSet barDataSet = new BarDataSet(barEntry,"Random Data");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);


        barChart.setVisibility(View.VISIBLE);
        barChart.animateY(5000);
        barChart.setData(barData);
        barChart.setFitBars(true);

        Description description = new Description();
        description.setText("Growth Rate Per Year");

        barChart.setDescription(description);
        barChart.invalidate();

       // pieChart.setVisibility(View.INVISIBLE);

    }
}
