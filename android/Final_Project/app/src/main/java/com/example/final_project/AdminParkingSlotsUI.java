package com.example.final_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class AdminParkingSlotsUI extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    int numberOfTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_parking_slot_ui);
        tabLayout = findViewById(R.id.main_tabLayout);
        viewPager = findViewById(R.id.main_pager);


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());



        //to get the parking area variable from previos step and start using it
        ParkingArea p =(ParkingArea) getIntent().getParcelableExtra("p");
        numberOfTabs = p.NumOfTaps(p.Column);
        //for loop and create tab based on ParkingArea Colum
        for (int i = 0; i<= numberOfTabs; i++){
            adapter.addTab(new MyTab("Column '"+i+"'",ColumnFragment.newInstance(i,(i+1),p.getID(), Integer.parseInt(p.Row()))));
            i++;
        }


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });






    }



}
