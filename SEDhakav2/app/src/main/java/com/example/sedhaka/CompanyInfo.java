package com.example.sedhaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CompanyInfo extends AppCompatActivity {
    private TextView name;
    private TextView code;
    private TextView ltp;
    private TextView ycp;
    private TextView tcp;
    private TextView volume;
    private TextView trade;
    private TextView value;
    private TextView high;
    private TextView low;
    private TextView change;
    private TextView changeP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        code=findViewById(R.id.code);
        name=findViewById(R.id.name);
        ltp=findViewById(R.id.ltp);
        ycp=findViewById(R.id.ycp);
        tcp=findViewById(R.id.tcp);
        volume=findViewById(R.id.volume);
        trade=findViewById(R.id.trade);
        value=findViewById(R.id.value);
        high=findViewById(R.id.highest);
        low=findViewById(R.id.lowest);
        change=findViewById(R.id.change);
        changeP=findViewById(R.id.changeP);
        String json= getIntent().getStringExtra("company");
        Gson string=new Gson();
        Type type = new TypeToken<Company>() {}.getType();
        Company company= string.fromJson(json, type);

        name.setText((company.getName()));
        code.setText(company.getCode());
        ltp.setText(company.getLtp().toString());
        ycp.setText(String.valueOf(company.getYcp()));
        tcp.setText(String.valueOf(company.getClosep()));
        volume.setText((String.valueOf(company.getVolume())));
        trade.setText(String.valueOf(company.getTrade()));
        value.setText(String.valueOf(company.getValue()));
        high.setText(String.valueOf(company.getHigh()));
        low.setText(String.valueOf(company.getLow()));
        change.setText(String.valueOf(company.getChange()));
        changeP.setText(String.valueOf(company.getChangeP())+"%");



    }
}
