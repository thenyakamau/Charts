package com.example.charts.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.charts.R;
import com.example.charts.model.ChartsModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {


    private BarChart barChart;
    private PieChart pieChart;
    private String method;
    private ArrayList<ChartsModel>response;
    private View view;
    private static final String TAG = "ChartFragment";

    public ChartFragment(String method, ArrayList<ChartsModel> response) {
        this.method = method;
        this.response = response;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_charts_fragment, container, false);

        barChart =  view.findViewById(R.id.bar_chart);
        pieChart = view.findViewById(R.id.pie_chart);

        if (method.equals("Bar Graph")){

            BarChart();

        }else if(method.equals("Pie Chart")){

            PieChart();

        }else {


            Toast.makeText(getContext(), "Error on Chart", Toast.LENGTH_SHORT).show();

        }


        return view;
    }

    private void BarChart(){

        Toast.makeText(getContext(), "This Bar Chart", Toast.LENGTH_SHORT).show();
        List<BarEntry> barEntry = new ArrayList<>();

        Log.d(TAG, "BarChart: Barchart");


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
        barChart.buildLayer();
        barChart.requestFocus();
        barChart.setExtraOffsets(5,10,5,5);



        pieChart.setVisibility(View.INVISIBLE);

    }



     private void PieChart(){

        List<PieEntry> pieEntry = new ArrayList<>();

        for(ChartsModel chartsModel: response){

            pieEntry.add(new PieEntry(chartsModel.getYear(), chartsModel.getGrowthRate()));

        }

        PieDataSet pieDataSet = new PieDataSet(pieEntry, "Random Data");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateXY(5000, 5000);
        pieChart.setData(pieData);
        //pieChart.setFitBars(true);

        Description description = new Description();
        description.setText("Growth Rate Per Year");

        pieChart.setDescription(description);
        pieChart.invalidate();

        barChart.setVisibility(View.INVISIBLE);
    }

}
