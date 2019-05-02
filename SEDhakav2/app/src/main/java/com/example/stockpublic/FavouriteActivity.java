package com.example.stockpublic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class FavouriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.getMenu().findItem(R.id.navigation_favourite).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_exit:
                        finish();
                        moveTaskToBack(true);
                        break;
                    case R.id.navigation_home:
                        Intent a = new Intent(FavouriteActivity.this, DrawerActivity.class);
                        startActivity(a);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_list:
                        Intent c = new Intent(FavouriteActivity.this,MainActivity.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }
}
