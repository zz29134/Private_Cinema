package com.mp.private_cinema.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mp.pc_library.viewpager_indicator.IconPagerAdapter;
import com.mp.private_cinema.R;
import com.mp.pc_library.base.BaseFragment;
import com.mp.private_cinema.fragment.Fragment_Cinema;
import com.mp.private_cinema.fragment.Fragment_Film;
import com.mp.private_cinema.fragment.Fragment_Home;
import com.mp.private_cinema.fragment.Fragment_Mine;

import java.util.ArrayList;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Adapter_MainFragment extends FragmentStatePagerAdapter implements IconPagerAdapter {

    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private static final String[] titles = new String[]{"首页", "影片", "院线", "我的"};
    private static final int[] ICONS = new int[] {
            R.drawable.home_indicator_icon,
            R.drawable.cinema_indicator_icon,
            R.drawable.film_indicator_icon,
            R.drawable.mine_indicator_icon,
    };

    public Adapter_MainFragment(FragmentManager fm) {
        super(fm);
        mFragments.add(Fragment_Home.newInstance());
        mFragments.add(Fragment_Film.newInstance());
        mFragments.add(Fragment_Cinema.newInstance());
        mFragments.add(Fragment_Mine.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }
}
