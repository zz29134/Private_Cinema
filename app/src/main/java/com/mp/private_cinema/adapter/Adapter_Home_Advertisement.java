package com.mp.private_cinema.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.mp.private_cinema.bean.Bean_Home_Advertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/21 <br/>
 * 说明
 */

public class Adapter_Home_Advertisement extends LoopPagerAdapter {

    private Context mContext;
    private List<Bean_Home_Advertisement> advertisements = new ArrayList<>();

    public Adapter_Home_Advertisement(RollPagerView viewPager, Context mContext, List<Bean_Home_Advertisement> advertisements) {
        super(viewPager);
        this.mContext = mContext;
        this.advertisements = advertisements;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //加载图片
        Glide.with(mContext)
                .load(advertisements.get(position).getADVERTISEMENT_IMAGEPATH())
                .into(imageView);
        return imageView;
    }

    @Override
    public int getRealCount() {
        return advertisements.size();
    }
}
