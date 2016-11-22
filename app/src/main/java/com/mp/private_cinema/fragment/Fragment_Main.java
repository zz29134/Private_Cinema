package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.mp.pc_library.viewpager_indicator.TabPageIndicator;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_MainFragment;
import com.mp.private_cinema.base.BaseFragment;

import butterknife.BindView;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Main extends BaseFragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;

    public static Fragment_Main newInstance() {
        Bundle args = new Bundle();
        Fragment_Main fragment = new Fragment_Main();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.main_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        viewPager.setAdapter(new Adapter_MainFragment(getChildFragmentManager()));
        indicator.setViewPager(viewPager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
