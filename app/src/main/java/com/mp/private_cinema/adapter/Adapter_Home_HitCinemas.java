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
import com.kevin.wraprecyclerview.BaseRecyclerAdapter;
import com.mp.private_cinema.R;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/7 <br/>
 * 说明
 */

public class Adapter_Home_HitCinemas extends BaseRecyclerAdapter<Bean_Home_HitCinemas, Adapter_Home_HitCinemas.ViewHolder> {

    public Adapter_Home_HitCinemas(Context context) {
        super(context);
    }

    public Adapter_Home_HitCinemas(Context mContext, LinkedList<Bean_Home_HitCinemas> mItemLists) {
        super(mContext, mItemLists);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_hitcinemas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position = position;

        holder.hitCinemaName.setText(mItemLists.get(position).getStore_name());
        holder.hitCinemaFeature.setText(mItemLists.get(position).getStore_telephone());
        holder.hitCinemaAddress.setText(mItemLists.get(position).getStore_adress());
        holder.rbCinemaRating.setRating(Float.parseFloat(mItemLists.get(position).getStore_score()) / 2);
        Glide.with(mContext).load(mItemLists.get(position).getPicture_address()).into(holder.hitCinemaImageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        int position;

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
        public void onClick(View view) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(view, position);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
