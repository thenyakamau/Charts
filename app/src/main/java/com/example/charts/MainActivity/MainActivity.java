package com.example.charts.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.charts.R;
import com.example.charts.TestActivity;
import com.example.charts.fragment.ChartFragment;
import com.example.charts.fragment.ChartFragmentPresenter;
import com.example.charts.fragment.ChartFragmentPresenterImplementer;
import com.example.charts.model.ChartsModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    ProgressDialog progressDialog;
    Button bar_btn, pie_btn, line_btn;
    ArrayList<ChartsModel> chartsModel;
    ChartFragmentPresenterImplementer chartFragmentPresenterImplementer;
    ChartFragmentPresenter chartFragmentPresenter;

    BarChart barChart;
    PieChart pieChart;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        chartFragmentPresenterImplementer = new ChartFragmentPresenterImplementer(this);
        chartFragmentPresenterImplementer.getChartData();

        barChart = findViewById(R.id.bar_chart3);
        pieChart = findViewById(R.id.pie_chart3);
        lineChart = findViewById(R.id.lineChart3);

        bar_btn = findViewById(R.id.bar_btn);
        bar_btn.setOnClickListener(view -> addBarChart());

        pie_btn = findViewById(R.id.pie_btn);
        pie_btn.setOnClickListener(view -> addPieChart());

        line_btn = findViewById(R.id.line_btn);
        line_btn.setOnClickListener(view -> addLineChart());
    }


    @Override
    public void showOnProgress() {

        progressDialog.show();

    }

    @Override
    public void hideProgress() {

        progressDialog.hide();

    }

    @Override
    public void onResults(ArrayList<ChartsModel> chartsModels) {
        this.chartsModel = chartsModels;

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        addChartFragment("Bar Graph", chartsModels);

        addBarChart();
    }

    @Override
    public void onError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    public void addChartFragment(String message, ArrayList<ChartsModel> chartsModel) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChartFragment chartFragment = new ChartFragment(message, chartsModel);
        fragmentTransaction.replace(R.id.home_frame, chartFragment);
        fragmentTransaction.commit();

    }

    public void addBarChart() {

        // addChartFragment("Bar Graph", chartsModel);

//        Intent intent = new Intent(this, TestActivity.class);
//        intent.putExtra("Chart List", chartsModel);
//        startActivity(intent);

        Toast.makeText(this, "This Bar Chart", Toast.LENGTH_SHORT).show();
        List<BarEntry> barEntry = new ArrayList<>();


        for (ChartsModel chartsModel : chartsModel) {

            barEntry.add(new BarEntry(chartsModel.getYear(), chartsModel.getGrowthRate()));

        }

        BarDataSet barDataSet = new BarDataSet(barEntry, "Random Data");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        BarChart barChart = findViewById(R.id.bar_chart3);

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
        barChart.setExtraOffsets(5, 10, 5, 5);

        LineChart lineChart = findViewById(R.id.lineChart3);
        PieChart pieChart = findViewById(R.id.pie_chart3);
        pieChart.setVisibility(View.INVISIBLE);
        lineChart.setVisibility(View.INVISIBLE);

    }

    private void addPieChart() {

        // addChartFragment("Pie Chart", chartsModel);

        List<PieEntry> pieEntry = new ArrayList<>();

        for (ChartsModel chartsModel : chartsModel) {

            pieEntry.add(new PieEntry(chartsModel.getYear(), chartsModel.getGrowthRate()));

        }

        PieDataSet pieDataSet = new PieDataSet(pieEntry, "Random Data");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        PieChart pieChart = findViewById(R.id.pie_chart3);
        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateXY(5000, 5000);
        pieChart.setData(pieData);
        pieChart.isDrawHoleEnabled();
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setEntryLabelColor(Color.DKGRAY);
        pieChart.setCenterText("Statistics");
        pieChart.requestFocus();

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieData.setValueTextSize(13f);
        pieData.setValueTextColor(Color.BLACK);

        //pieChart.setFitBars(true);

        Description description = new Description();
        description.setText("Growth Rate Per Year");

        pieChart.setDescription(description);
        pieChart.invalidate();

        LineChart lineChart = findViewById(R.id.lineChart3);

        barChart.setVisibility(View.INVISIBLE);
        lineChart.setVisibility(View.INVISIBLE);

    }

    public void addLineChart(){


        Toast.makeText(this, "This Bar Chart", Toast.LENGTH_SHORT).show();
        List<Entry> lineEntry = new ArrayList<>();


        for (ChartsModel chartsModel : chartsModel) {

            lineEntry.add(new Entry(chartsModel.getYear(), chartsModel.getGrowthRate()));

        }

        LineDataSet lineDataSet = new LineDataSet(lineEntry, "Random Data");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        LineData lineData = new LineData(lineDataSet);
        //lineData.setLineWidth(0.9f);

        LineChart lineChart = findViewById(R.id.lineChart3);

        lineChart.setVisibility(View.VISIBLE);
        lineChart.animateY(5000);
        lineChart.setData(lineData);


        Description description = new Description();
        description.setText("Growth Rate Per Year");

        lineChart.setDescription(description);
        lineChart.invalidate();
        lineChart.buildLayer();
        lineChart.requestFocus();
        lineChart.setExtraOffsets(5, 10, 5, 5);

        LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(false);

        lineData.setValueTextSize(13f);
        lineData.setValueTextColor(Color.BLACK);

        PieChart pieChart = findViewById(R.id.pie_chart3);
        pieChart.setVisibility(View.INVISIBLE);
        barChart.setVisibility(View.INVISIBLE);


    }

}
