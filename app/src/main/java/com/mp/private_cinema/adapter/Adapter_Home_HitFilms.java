package com.mp.private_cinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.mp.private_cinema.R;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/11/29 <br/>
 * 说明
 */

public class Adapter_Home_HitFilms extends RecyclerView.Adapter<Adapter_Home_HitFilms.ViewHolder> {

    private Context context;
    private List<Bean_Home_HitFilms> hitFilmsList = new ArrayList<>();

    public Adapter_Home_HitFilms(Context context, List<Bean_Home_HitFilms> hitFilmsList) {
        this.context = context;
        this.hitFilmsList = hitFilmsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_hitfilms, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(context).load(hitFilmsList.get(position).getPicture_address()).into(holder.imageView);
        holder.tvFilmName.setText(hitFilmsList.get(position).getMovie_name());
        holder.tvFilmScore.setText(hitFilmsList.get(position).getScore());
        holder.rbFilmRating.setRating(Float.parseFloat(hitFilmsList.get(position).getScore()) / 2);

        if (null != onItemClickListener) {
            holder.home_item_hitfilms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, hitFilmsList.get(holder.getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hitFilmsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_item_hitfilms)
        LinearLayout home_item_hitfilms;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tv_filmName)
        TextView tvFilmName;
        @BindView(R.id.rb_filmRating)
        SimpleRatingBar rbFilmRating;
        @BindView(R.id.tv_filmScore)
        TextView tvFilmScore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addData(List<Bean_Home_HitFilms> hitFilms) {
        hitFilmsList.addAll(hitFilms);
        this.notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Bean_Home_HitFilms hitFilms);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
