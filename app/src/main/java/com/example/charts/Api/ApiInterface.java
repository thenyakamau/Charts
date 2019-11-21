package com.example.charts.Api;

import com.example.charts.model.ChartsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getChartData.php")
    Call<ArrayList<ChartsModel>>getChartData();

}
