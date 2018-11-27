package com.example.leftie.Essapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.leftie.Essapp.Fragments.feedfragment;
import com.example.leftie.Essapp.Fragments.infofragment;
import com.example.leftie.Essapp.Fragments.inquiriesfragment;
import com.example.leftie.Essapp.Fragments.leavefragment;


public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new feedfragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_feed:
                   selectedFragment = new feedfragment();
                    break;

                case R.id.nav_personal:
                    selectedFragment = new infofragment();
                    break;

                case R.id.nav_feedback:
                    selectedFragment = new inquiriesfragment();
                    break;


                case R.id.nav_leave:
                    selectedFragment = new leavefragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;

        }
    };

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }
}