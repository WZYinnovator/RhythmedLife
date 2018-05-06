package com.example.harleycody.rhythmedlife.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment_Main fragment_main;
    private Fragment_Add fragment_add;
    private Fragment_My_Profile fragment_my_profile;
    private Fragment[] fragments;
    private int lastShowFragment=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                if (lastShowFragment != 0) {
                    switchFragment(lastShowFragment,0);
                    lastShowFragment = 0;
                }
                return true;
            } else if (i == R.id.navigation_dashboard) {
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, 1);
                    lastShowFragment = 1;
                }
                return true;
            } else if (i == R.id.navigation_notifications) {
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2);
                    lastShowFragment = 2;
                }
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    public void switchFragment(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if(!fragments[index].isAdded()){
            transaction.replace(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commit();
    }

    private void initFragments(){
        fragment_main = new Fragment_Main();
        fragment_add = new Fragment_Add();
        fragment_my_profile = new Fragment_My_Profile();
        fragments = new Fragment[]{fragment_main, fragment_add, fragment_my_profile};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment_main)
                .show(fragment_main)
                .commit();
    }
}

