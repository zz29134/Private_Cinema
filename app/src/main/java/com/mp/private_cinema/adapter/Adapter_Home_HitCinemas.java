package com.mp.private_cinema.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.private_cinema.R;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/7 <br/>
 * 说明
 */

public class Adapter_Home_HitCinemas extends UltimateViewAdapter {

    private Context mContext;
    private List<Bean_Home_HitCinemas> cinemases = new ArrayList<>();

    public Adapter_Home_HitCinemas(Context mContext, List<Bean_Home_HitCinemas> cinemases) {
        this.mContext = mContext;
        this.cinemases = cinemases;
    }

    public List<Bean_Home_HitCinemas> getCinemases() {
        return cinemases;
    }

    public void setCinemases(List<Bean_Home_HitCinemas> cinemases) {
        this.cinemases = cinemases;
    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        return new UltimateRecyclerviewViewHolder<>(view);
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return new UltimateRecyclerviewViewHolder<>(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_hitcinemas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return cinemases.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= cinemases.size() : position < cinemases.size())
                && (customHeaderView == null || position > 0)) {
            position -= customHeaderView == null ? 0 : 1;
            ((ViewHolder) holder).hitCinemaName.setText(cinemases.get(position).getStore_name());
            ((ViewHolder) holder).hitCinemaFeature.setText(cinemases.get(position).getStore_telephone());
            ((ViewHolder) holder).hitCinemaAddress.setText(cinemases.get(position).getStore_adress());
            ((ViewHolder) holder).rbCinemaRating.setRating(Float.parseFloat(cinemases.get(position).getStore_score()) / 2);
            Glide.with(mContext).load(cinemases.get(position).getPicture_address()).into(((ViewHolder) holder).hitCinemaImageView);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends UltimateRecyclerviewViewHolder {
        @BindView(R.id.hitCinema_ImageView)
        ImageView hitCinemaImageView;
        @BindView(R.id.hitCinema_Name)
        TextView hitCinemaName;
        @BindView(R.id.hitCinema_Address)
        TextView hitCinemaAddress;
        @BindView(R.id.hitCinema_Feature)
        TextView hitCinemaFeature;
        @BindView(R.id.rb_cinemaRating)
        SimpleRatingBar rbCinemaRating;
        @BindView(R.id.home_item_hitcinemas)
        CardView homeItemHitcinemas;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onItemSelected() {
            ToastUtils.show(mContext, hitCinemaName.getText());
        }
    }
}
