package com.example.sedhaka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Company> companyList=new ArrayList<Company>();
    public static ListView lv;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.companyList);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.getMenu().findItem(R.id.navigation_list).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_exit:
                        finish();
                        moveTaskToBack(true);
                        break;
                    case R.id.navigation_favourite:
                        Intent b = new Intent(MainActivity.this,FavouriteActivity.class);
                        startActivity(b);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_home:
                        Intent c = new Intent(MainActivity.this,DrawerActivity.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        break;

                }
                return false;
            }
        });
        CollectInfo CompanyList = new CollectInfo(companyList, this, adapter, false, lv);
        CompanyList.execute();
        MainActivity.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                // We know the View is a <extView so we can cast it
                if(adapter==null){
                    adapter=new ListAdapter(getContext(),R.layout.adapter_view,companyList);
                }
                Company selItem = (Company) adapter.getItem(position);
                Gson string=new Gson();
                String json=string.toJson(selItem);
                Intent a=new Intent(getContext(),CompanyInfo.class);
                a.putExtra("company",json);
                startActivity(a);

            }
        });

    }



    private Context getContext(){
        return this;
    }
}
