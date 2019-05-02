package com.example.stockpublic;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Company> implements Filterable {
    private ArrayList<Company> companyList;
    private static final String TAG="CompanyListAdapter";
    private Filter companyFilter;
    private Context mContext;
    private ArrayList<Company> oriList;
    int mResources;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Company> objects) {
        super(context, resource, objects);
        oriList=objects;
        mContext=context;
        companyList=objects;
        mResources=resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        String name=getItem(position).getName();
        String code=getItem(position).getCode();
        double ltp=getItem(position).getLtp();
        double change=getItem(position).getChange();
        double changeP=getItem(position).getChangeP();
        Company company=new Company(code,ltp,change,changeP);
        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResources,parent,false);
        TextView tltp=(TextView)convertView.findViewById(R.id.ltp);
        TextView tcode=(TextView)convertView.findViewById(R.id.code);
        TextView tchange=(TextView)convertView.findViewById(R.id.change);
        TextView tchangeP=(TextView)convertView.findViewById(R.id.changeP);
        TextView tName=(TextView)convertView.findViewById(R.id.name);
        tcode.setText("("+code+")");
        if(code.startsWith("UP")){
            System.out.println(code);
        }
        if(change<0)tchange.setTextColor(Color.RED);
        else tchange.setTextColor(Color.GREEN);
        if(changeP<0)tchangeP.setTextColor(Color.RED);
        else tchangeP.setTextColor(Color.GREEN);
        tchange.setText("Change: "+ String.valueOf(change));
        tchangeP.setText(String.valueOf(changeP+"%"));
        tltp.setText("LTP: "+ String.valueOf(ltp));
        tName.setText(String.valueOf(name));
        return convertView;
    }
    public int getCount() {
        return companyList.size();
    }

    public Company getItem(int position) {
        return companyList.get(position);
    }

    public long getItemId(int position) {

        return companyList.get(position).hashCode();
    }
    public void resetData() {
        companyList = oriList;
    }


}
