package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mp.pc_library.viewpager_indicator.CirclePageIndicator;
import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment_NoData;
import com.mp.private_cinema.utils.Constants;
import com.mp.private_cinema.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明
 */

public class Fragment_Navigator extends BaseFragment_NoData {

    ArrayList<View> views = new ArrayList<View>();

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    public static Fragment_Navigator newInstance() {
        Bundle args = new Bundle();
        Fragment_Navigator fragment = new Fragment_Navigator();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.navigator_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        views.add(LayoutInflater.from(getContext()).inflate(R.layout.navigator_1st, null));
        views.add(LayoutInflater.from(getContext()).inflate(R.layout.navigator_2nd, null));
        views.add(LayoutInflater.from(getContext()).inflate(R.layout.navigator_3rd, null));
        (views.get(2).findViewById(R.id.textView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_Logo.newInstance());
                SPUtils.put(getContext(), Constants.FLAG_NAVIGATOR, false);
            }
        });

        MyNavigatorAdapter myNavigatorAdapter = new MyNavigatorAdapter(views);
        viewPager.setAdapter(myNavigatorAdapter);
        indicator.setFillColor(ContextCompat.getColor(getContext(), R.color.ameber400));
        indicator.setPageColor(ContextCompat.getColor(getContext(), R.color.cyan700));
        indicator.setRadius(15.0F);
        indicator.setViewPager(viewPager);

    }

    private class MyNavigatorAdapter extends PagerAdapter {

        ArrayList<View> views = new ArrayList<View>();

        public MyNavigatorAdapter(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
