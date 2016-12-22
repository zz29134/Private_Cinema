package com.mp.private_cinema.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mp.private_cinema.R;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/21 <br/>
 * 说明
 */

public class HitCinemasViewHolder extends BaseViewHolder<Bean_Home_HitCinemas> {

    private ImageView picture;
    private TextView name;
    private TextView address;
    private TextView feature;
    private SimpleRatingBar rating;

    public HitCinemasViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_item_hitcinemas);
        picture = $(R.id.hitCinema_ImageView);
        name = $(R.id.hitCinema_Name);
        address = $(R.id.hitCinema_Address);
        feature = $(R.id.hitCinema_Feature);
        rating = $(R.id.rb_cinemaRating);
    }

    @Override
    public void setData(Bean_Home_HitCinemas data) {
        Glide.with(getContext()).load(data.getPICTURE_ADDRESS()).into(picture);
        name.setText(data.getCINEMA_NAME());
        address.setText(data.getCINEMA_ADDRESS());
        feature.setText(data.getCINEMA_TELEPHONE());
        rating.setRating(Float.parseFloat(data.getCINEMA_SCORE()) / 2);
    }
}
