package com.example.final_project;


import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.android.volley.RequestQueue;
import com.example.final_project.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import java.util.concurrent.CountDownLatch;

public class UserParkingSlotsUI extends AppCompatActivity {
    final CountDownLatch countDownLatch = new CountDownLatch(1);
    final Object[] responseHolder = new Object[1];


    private RequestQueue requestQueue;
    private static final String URL = "http://carparking1.online/GetParkingSpots.php";

    ActivityMainBinding binding;

public int i;
    public int j;


    TabLayout tabLayout;
    ViewPager viewPager;
    int numberOfTabs;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_parking_slots_ui);
        tabLayout = findViewById(R.id.main_tabLayout);
        viewPager = findViewById(R.id.main_pager);


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());



        ParkingArea p =(ParkingArea) getIntent().getParcelableExtra("p");
        numberOfTabs = p.NumOfTaps(p.Column);
        String tabname = "Column :"+i+"&"+j;
        //for loop and create tab based on ParkingArea Colum
        for (i = 1,j=2; i<= numberOfTabs*2; i=i+2,j=j+2){

            String name = "Coulmn : "+i+" & "+j;
            MyTab tab = new MyTab(name,UserColumnFragment.newInstance(String.valueOf(i),String.valueOf(j),p.getID(), Integer.parseInt(p.Row())));

            System.out.println(tab.getTabName()+"TabName");
            adapter.addTab(tab);

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
