

package com.example.charts.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartsModel implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("growth_rate")
    @Expose
    private int growthRate;
    public final static Parcelable.Creator<ChartsModel> CREATOR = new Creator<ChartsModel>() {

        public ChartsModel createFromParcel(Parcel in) {
            return new ChartsModel(in);
        }

        public ChartsModel[] newArray(int size) {
            return (new ChartsModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7449734241988835196L;

     private ChartsModel(Parcel in) {
        this.id = (int) in.readValue((int.class.getClassLoader()));
        this.year = ((int) in.readValue((int.class.getClassLoader())));
        this.growthRate = ((int) in.readValue((int.class.getClassLoader())));
    }


    public ChartsModel(int id, int year, int growthRate) {
        super();
        this.id = id;
        this.year = year;
        this.growthRate = growthRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(year);
        dest.writeValue(growthRate);
    }

    public int describeContents() {
        return 0;
    }

}