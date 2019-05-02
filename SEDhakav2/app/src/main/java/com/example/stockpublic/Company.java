package com.example.stockpublic;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    private String code;
    private String name;
    private double ltp;
    private double change;
    private double changeP;
    private double closep;
    private double high;
    private double low;
    private double ycp;
    private double trade;
    private double value;
    private boolean isFav;
    private double volume;
    Company(String code, double ltp, double change, double changeP){
        this.code=code;
        this.name=name;
        this.ltp=ltp;
        this.change=change;
        this.changeP=changeP;
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public double getClosep() {
        return closep;
    }

    public double getVolume() {
        return volume;
    }

    Company(String code, double ltp, double high, double low, double closep, double ycp, double change, double changeP, double trade, double value, double volume){
        this.code=code;
        this.name=name;
        this.ltp=ltp;
        this.change=change;
        this.changeP=changeP;
        this.high=high;
        this.low=low;
        this.ycp=ycp;
        this.trade=trade;
        this.value=value;
        this.closep=closep;
        this.volume=volume;
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeString(code);
        dest.writeString(name);
        dest.writeDouble(ltp);
        dest.writeDouble(change);
        dest.writeDouble(changeP);
    }
    public Company(Parcel in) {
        code = in.readString();
        name = in.readString();
        ltp=in.readDouble();
        change=in.readDouble();
        changeP=in.readDouble();
    }

    public String getName() {
        return name;
    }

    public Double getLtp() {
        return ltp;
    }

    public Double getChange() {
        return change;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getYcp() {
        return ycp;
    }

    public double getTrade() {
        return trade;
    }

    public double getValue() {
        return value;
    }

    public Double getChangeP() {
        return changeP;
    }

    public String getCode() {
        return code;
    }
    public void setName(String name) {
        this.name=name;
    }
}