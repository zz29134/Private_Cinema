package com.mp.private_cinema.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/25
 * 说明
 */

public class Adapter_ViewPager_ImageView extends PagerAdapter {

    private ArrayList<ImageView> imageViewList = new ArrayList<>();

    public Adapter_ViewPager_ImageView(ArrayList<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
