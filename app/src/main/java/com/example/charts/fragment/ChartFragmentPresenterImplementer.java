package com.example.charts.fragment;

import com.example.charts.Api.ApiClient;
import com.example.charts.Api.ApiInterface;
import com.example.charts.MainActivity.MainView;
import com.example.charts.model.ChartsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartFragmentPresenterImplementer implements ChartFragmentPresenter {

    private MainView mainView;

    public ChartFragmentPresenterImplementer(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getChartData() {

        mainView.showOnProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArrayList<ChartsModel>> call = apiInterface.getChartData();
        call.enqueue(new Callback<ArrayList<ChartsModel>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<ChartsModel>> call, @NotNull Response<ArrayList<ChartsModel>> response) {

                mainView.hideProgress();

                if (response.isSuccessful() && response.body() != null) {


                    mainView.onResults(response.body());


                } else {

                    mainView.onError("Error on Get...");

                }

            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<ChartsModel>> call, @NotNull Throwable t) {


                mainView.hideProgress();
                mainView.onError(t.getLocalizedMessage());

            }
        });


    }

    public void getLocalData() {


        ArrayList<ChartsModel> list = new ArrayList<>();

        list.add(new ChartsModel(1, 2010, 10));
        list.add(new ChartsModel(2, 2011, 20));
        list.add(new ChartsModel(3, 2012, 30));
        list.add(new ChartsModel(4, 2013, 40));
        list.add(new ChartsModel(5, 2014, 50));
        list.add(new ChartsModel(6, 2015, 60));

        mainView.onResults(list);

    }
}
