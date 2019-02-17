package com.example.weatherreport;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProgramListPageAdapter extends FragmentPagerAdapter {
    public ProgramListPageAdapter(FragmentManager fm) {
        super(fm);


    }

    static public ArrayList<Fragment> arrayListFragment= new ArrayList<>();

    @Override
    public Fragment getItem(int i) {
        return arrayListFragment.get(i);
    }

    @Override
    public int getCount() {
        return arrayListFragment.size();
    }

    public void replacePage(Fragment fragment,int position) {
        arrayListFragment.add(position, fragment);
        notifyDataSetChanged();
    }

    public void removeInfoPage(){
        if(arrayListFragment.size()==2){
            arrayListFragment.remove(1);
        notifyDataSetChanged();
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
