package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter{
    ArrayList<MyTab> tabs = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addTab(MyTab tab){
        tabs.add(tab);

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getFragment();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        System.out.println(tabs.get(position).getTabName()+"TAB NAME");
        return tabs.get(position).getTabName();

    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
