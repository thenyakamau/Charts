package com.example.charts.MainActivity;

import com.example.charts.model.ChartsModel;

import java.util.ArrayList;

public interface MainView {

    void showOnProgress();
    void hideProgress();
    void onResults(ArrayList<ChartsModel>chartsModels);
    void onError(String message);

}
